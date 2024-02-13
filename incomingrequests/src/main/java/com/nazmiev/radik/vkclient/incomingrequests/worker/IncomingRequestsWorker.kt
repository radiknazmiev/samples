package com.nazmiev.radik.vkclient.incomingrequests.worker

import android.content.Context
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.Data
import androidx.work.WorkerParameters
import androidx.work.workDataOf
import com.nazmiev.radik.vkclient.core.NotificationBuilder
import com.nazmiev.radik.vkclient.core.db.models.MessageWithAttachments
import com.nazmiev.radik.vkclient.core.db.models.TaskHistory
import com.nazmiev.radik.vkclient.core.http.criticalErrors
import com.nazmiev.radik.vkclient.core.http.models.User
import com.nazmiev.radik.vkclient.core.ui.activitys.TaskSettingsActivity.Companion.FIELD_TASK_ID
import com.nazmiev.radik.vkclient.core.ui.activitys.TaskSettingsActivity.Companion.FIELD_TASK_IS_REPEAT
import com.nazmiev.radik.vkclient.core.ui.activitys.TaskSettingsActivity.Companion.FIELD_TASK_REPEAT_PERIOD
import com.nazmiev.radik.vkclient.core.ui.activitys.TaskSettingsActivity.Companion.FIELD_TASK_SEND_ACCOMPANYING_MESSAGES
import com.nazmiev.radik.vkclient.core.usecases.DateUseCase
import com.nazmiev.radik.vkclient.core.usecases.FriendUseCase
import com.nazmiev.radik.vkclient.core.usecases.MessageUseCase
import com.nazmiev.radik.vkclient.core.usecases.ProxyUseCase
import com.nazmiev.radik.vkclient.core.usecases.SharedPreferencesUseCase
import com.nazmiev.radik.vkclient.core.usecases.TaskUseCase
import com.nazmiev.radik.vkclient.core.usecases.UserUseCase
import com.nazmiev.radik.vkclient.core.utils.Antispam
import com.nazmiev.radik.vkclient.core.utils.Constants
import com.nazmiev.radik.vkclient.core.utils.Constants.TASK_TYPE_ACCEPT_FRIENDS_REQUESTS
import com.nazmiev.radik.vkclient.incomingrequests.R
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import java.util.Calendar
import java.util.concurrent.atomic.AtomicInteger
import com.nazmiev.radik.vkclient.core.db.models.User as DbUser

@HiltWorker
class IncomingRequestsWorker @AssistedInject constructor(
    @Assisted private val appContext: Context,
    @Assisted private val params: WorkerParameters,
    private val sharedPreferencesUseCase: SharedPreferencesUseCase,
    private val taskUseCase: TaskUseCase,
    private val proxyUseCase: ProxyUseCase,
    private val dateUseCase: DateUseCase,
    private val friendUseCase: FriendUseCase,
    private val messageUseCase: MessageUseCase,
    private val userUseCase: UserUseCase
) : CoroutineWorker(appContext, params) {

    private var accountNumber: AtomicInteger = AtomicInteger(0)
    private var progress: AtomicInteger = AtomicInteger(0)
    private var messages: List<MessageWithAttachments> = listOf()
    private var taskId: Int? = null
    private var isRepeat: Boolean = false
    private var repeatPeriod: Long = 0

    override suspend fun doWork(): Result {
        //TODO продолжить эксперимент с overlay window
        val window = Window(appContext)
        window.open()

        taskId = inputData.getInt(FIELD_TASK_ID, 0)
        isRepeat = inputData.getBoolean(FIELD_TASK_IS_REPEAT, false)
        repeatPeriod = inputData.getLong(FIELD_TASK_REPEAT_PERIOD, 0)
        val isSendMessage = inputData.getBoolean(FIELD_TASK_SEND_ACCOMPANYING_MESSAGES, false)
        val taskUsers = taskUseCase.getTaskUsers(taskId!!)

        val currentDate = dateUseCase.getCurrentDate()

        val dates = taskUseCase.getTaskHistoryDates(TASK_TYPE_ACCEPT_FRIENDS_REQUESTS)
        if (dates.size > 7) {
            taskUseCase.deleteTaskHistory(TASK_TYPE_ACCEPT_FRIENDS_REQUESTS, dates[0])
        }

        sendNotification()

        setProgress(workDataOf(Progress to progress.get()))
        addTaskHistory(
            appContext.getString(R.string.core_txt_start_account_processing),
            currentDate,
            0,
            1
        )

        if (sharedPreferencesUseCase.isUseMultiThreading()) {

            coroutineScope {
                taskUsers.map { user ->
                    async {
                        delay(1000)
                        accountProcessing(
                            user,
                            currentDate,
                            isSendMessage,
                            taskUsers.size,
                            taskUsers.indexOf(user) + 1
                        )
                    }
                }.awaitAll()
            }

        } else {
            taskUsers.forEach { user ->
                accountProcessing(user, currentDate, isSendMessage, taskUsers.size, 1)
            }
        }

        taskUseCase.deleteTask(taskId!!)
        if (isRepeat) {
            addNewTask()
        }

        setProgress(workDataOf(Progress to progress.get()))
        setProgress(workDataOf(TaskProgress to appContext.getString(R.string.core_txt_processing_complete)))
        addTaskHistory(
            appContext.getString(R.string.core_txt_processing_complete),
            currentDate,
            0,
            1
        )

        return Result.success()
    }

    private suspend fun accountProcessing(
        user: DbUser,
        currentDate: String,
        isSendMessage: Boolean,
        allSize: Int,
        threadNumber: Int
    ) {
        var offset = 0
        var itemsMaxCount = 0

        accountNumber.incrementAndGet()

        //TODO надо поменять связь по id
        messages = messageUseCase.getWelcomeMessage(user.id)

        val progressString = appContext.getString(
            R.string.core_txt_task_account_string, user.accountTitle ?: threadNumber
        )
        setProgress(workDataOf(TaskProgress to progress.get()))
        addTaskHistory(progressString, currentDate, user.login!!.toInt(), threadNumber)
        sendNotification()

        friendUseCase.initProxiedRepository(proxyUseCase.getProxyHttpClient(user.login?.toInt()!!))

        mainLoop@ while (true) {
            delay(sharedPreferencesUseCase.getPauseMSeconds().toLong())

            addTaskHistory(
                appContext.getString(R.string.txt_getting_list_requests),
                currentDate,
                user.login!!.toInt(),
                threadNumber
            )
            setProgress(workDataOf(Progress to progress.get()))
            //иначе не будет дергаться observer
            setProgress(workDataOf(TaskProgress to appContext.getString(R.string.txt_getting_list_requests)))

            val result = friendUseCase.getIncomingRequests(user.accessToken!!, true, offset)
            if (result.error != null) {
                val data = Data.Builder()
                data.putString("error_code", result.error!!.errorCode.toString())
                Result.failure(data.build())
                break
            } else {

                if (itemsMaxCount == 0) {
                    itemsMaxCount = result.response?.count!!
                }

                for (i in 0 until result.response?.items?.size!!) {
                    val friendId = result.response!!.items[i]

                    addTaskHistory(
                        appContext.getString(R.string.txt_accepting_request_from, friendId),
                        currentDate,
                        user.login!!.toInt(),
                        threadNumber
                    )
                    setProgress(workDataOf(Progress to progress.get()))
                    setProgress(
                        workDataOf(
                            TaskProgress to appContext.getString(
                                R.string.txt_accepting_request_from,
                                friendId
                            )
                        )
                    )

                    val addFriendRequest = friendUseCase.addFriend(user.accessToken!!, friendId)

                    if (addFriendRequest.error != null) {
                        val error = addFriendRequest.error!!
                        val errorMsg = "${error.errorCode} ${error.errorMsg} <br>"

                        addTaskHistory(errorMsg, currentDate, user.login!!.toInt(), threadNumber)
                        setProgress(workDataOf(Progress to progress.get()))
                        setProgress(workDataOf(TaskProgress to errorMsg))

                        if (criticalErrors.containsKey(error.errorCode)) {
                            val data = Data.Builder()
                            data.putString(
                                "error_code", addFriendRequest.error!!.errorCode.toString()
                            )

                            Result.failure(data.build())

                            break@mainLoop

                        } else {
                            delay(sharedPreferencesUseCase.getPauseMSeconds().toLong())
                            continue
                        }
                    } else {
                        if (isSendMessage) {
                            delay(1000)
                            val friend = userUseCase.getUserInfo(user.accessToken!!, listOf(friendId.toString()), listOf())[0]
                            sendMessage(friend, user.login!!.toInt(), user.accessToken!!, threadNumber)
                        }
                    }

                    delay(sharedPreferencesUseCase.getPauseMSeconds().toLong())
                }

                if (offset >= itemsMaxCount) {
                    break
                }
            }

            offset += 1000
        }

        progress = AtomicInteger(accountNumber.get() * 100 / allSize)
    }

    private suspend fun sendNotification() {
        setForeground(
            NotificationBuilder.createNotification(
                applicationContext, "VKClient:Worker", "Worker", progress.get(), 1
            )
        )
    }

    private suspend fun addTaskHistory(
        historyText: String,
        currentDate: String,
        userId: Int,
        threadNumber: Int
    ) {
        taskUseCase.addTaskHistory(
            TaskHistory(
                taskType = TASK_TYPE_ACCEPT_FRIENDS_REQUESTS,
                historyText = historyText,
                currentDate = currentDate,
                historyTime = dateUseCase.getCurrentDateTime(),
                userId = userId,
                threadNumber = threadNumber
            )
        )
    }

    private suspend fun sendMessage(
        friend: User,
        userId: Int,
        accessToken: String,
        threadNumber: Int
    ) {
        val currentDate = dateUseCase.getCurrentDate()

        addTaskHistory(
            appContext.getString(R.string.core_txt_sending_message),
            currentDate,
            userId,
            threadNumber
        )
        setProgress(workDataOf(Progress to progress.get()))
        setProgress(workDataOf(TaskProgress to appContext.getString(R.string.core_txt_sending_message)))

        val seconds = Calendar.getInstance().time.time / 1000
        messages.forEach {
            var messageText = it.messageText?.replace("%user_name%", friend.firstName!!)
            messageText = messageText?.let { message ->
                Antispam(
                    message,
                    sharedPreferencesUseCase.getIsUseLatinReplace()
                )
            }?.start()
            if (messageText != null) {
                val messageAttachment = messageUseCase.getMessageAttachmentsString(it.attachments)
                val result = messageUseCase.sendMessage(
                    friend.id!!,
                    seconds.toInt(),
                    messageText,
                    messageAttachment,
                    accessToken = accessToken
                )

                if (result.error != null) {
                    val error = result.error!!
                    val errorMsg = "${error.errorCode} ${error.errorMsg} <br>"

                    setProgress(workDataOf(Progress to progress.get()))
                    setProgress(workDataOf(TaskProgress to errorMsg))
                }
            }
        }

    }

    private suspend fun addNewTask() {
        val newTaskId = taskUseCase.addNewTask(
            TASK_TYPE_ACCEPT_FRIENDS_REQUESTS,
            appContext.getString(com.nazmiev.radik.vkclient.core.R.string.core_txt_accept_incoming_requests),
            dateUseCase.getFutureDateTime(repeatPeriod.toInt())
        )

        val usersId = taskUseCase.getTaskUsers(taskId!!).map { it.id }
        taskUseCase.addUsersToTask(newTaskId.toInt(), usersId)

        taskUseCase.setTaskStatus(newTaskId.toInt(), Constants.TASK_STATUS_PLANNED)
    }

    companion object {
        const val Progress = "Progress"
        const val TaskProgress = "TaskProgress"
    }

}