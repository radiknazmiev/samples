package com.nazmiev.radik.vkclient.incomingrequests.worker

import android.content.Context
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.Data
import androidx.work.WorkerParameters
import androidx.work.workDataOf
import com.nazmiev.radik.vkclient.core.Constants.TASK_TYPE_ACCEPT_FRIENDS_REQUESTS
import com.nazmiev.radik.vkclient.core.NotificationBuilder
import com.nazmiev.radik.vkclient.core.db.models.TaskHistory
import com.nazmiev.radik.vkclient.core.http.criticalErrors
import com.nazmiev.radik.vkclient.core.ui.activitys.TaskSettingsActivity.Companion.FIELD_TASK_ID
import com.nazmiev.radik.vkclient.core.ui.activitys.TaskSettingsActivity.Companion.FIELD_TASK_SEND_ACCOMPANYING_MESSAGES
import com.nazmiev.radik.vkclient.core.usecases.CaptchaUseCase
import com.nazmiev.radik.vkclient.core.usecases.DateUseCase
import com.nazmiev.radik.vkclient.core.usecases.FriendUseCase
import com.nazmiev.radik.vkclient.core.usecases.MessageUseCase
import com.nazmiev.radik.vkclient.core.usecases.ProxyUseCase
import com.nazmiev.radik.vkclient.core.usecases.SharedPreferencesUseCase
import com.nazmiev.radik.vkclient.core.usecases.TaskUseCase
import com.nazmiev.radik.vkclient.incomingrequests.R
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import kotlinx.coroutines.delay
import java.util.Calendar

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
    private val captchaUseCase: CaptchaUseCase
) : CoroutineWorker(appContext, params) {

    private var offset = 0
    private var itemsMaxCount = 0
    private var accountNumber = 0
    private var progress = 0

    override suspend fun doWork(): Result {
        //TODO продолжить эксперимент с overlay window
        val window = Window(appContext)
        window.open()

        val taskId = inputData.getInt(FIELD_TASK_ID, 0)
        val isSendMessage = inputData.getBoolean(FIELD_TASK_SEND_ACCOMPANYING_MESSAGES, false)
        val taskUsers = taskUseCase.getTaskUsers(taskId)

        val currentDate = dateUseCase.getCurrentDate()

        val dates = taskUseCase.getTaskHistoryDates(TASK_TYPE_ACCEPT_FRIENDS_REQUESTS)
        if (dates.size > 7) {
            taskUseCase.deleteTaskHistory(TASK_TYPE_ACCEPT_FRIENDS_REQUESTS, dates[0])
        }

        sendNotification()

        setProgress(workDataOf(Progress to progress))
        addTaskHistory(appContext.getString(R.string.core_txt_start_account_processing), currentDate, 0)

        taskUsers.forEach { user ->

            accountNumber++

            val progressString = appContext.getString(
                R.string.core_txt_task_account_string, user.accountTitle ?: accountNumber
            )
            setProgress(workDataOf(TaskProgress to progress))
            addTaskHistory(progressString, currentDate, user.login!!.toInt())
            sendNotification()

            friendUseCase.initProxiedRepository(proxyUseCase.getProxyHttpClient(user.login?.toInt()!!))

            mainLoop@ while (true) {
                delay(sharedPreferencesUseCase.getPauseMSeconds().toLong())

                addTaskHistory(
                    appContext.getString(R.string.txt_getting_list_requests),
                    currentDate,
                    user.login!!.toInt()
                )
                setProgress(workDataOf(Progress to progress))
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
                            user.login!!.toInt()
                        )
                        setProgress(workDataOf(Progress to progress))
                        setProgress(workDataOf(TaskProgress to appContext.getString(R.string.txt_accepting_request_from, friendId)))

                        val addFriendRequest = friendUseCase.addFriend(user.accessToken!!, friendId)

                        if (addFriendRequest.error != null) {
                            val error = addFriendRequest.error!!
                            val errorMsg = "${error.errorCode} ${error.errorMsg} <br>"

                            addTaskHistory(errorMsg, currentDate, user.login!!.toInt())
                            setProgress(workDataOf(Progress to progress))
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
                                sendMessage(friendId)
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

            progress = accountNumber * 100 / taskUsers.size
        }

        taskUseCase.deleteTask(taskId)

        setProgress(workDataOf(Progress to progress))
        setProgress(workDataOf(TaskProgress to appContext.getString(R.string.core_txt_processing_complete)))
        addTaskHistory(appContext.getString(R.string.core_txt_processing_complete), currentDate, 0)

        return Result.success()
    }

    private suspend fun sendNotification() {
        setForeground(
            NotificationBuilder.createNotification(
                applicationContext, "VKClient:Worker", "Worker", progress, 1
            )
        )
    }

    private suspend fun addTaskHistory(historyText: String, currentDate: String, userId: Int) {
        taskUseCase.addTaskHistory(
            TaskHistory(
                taskType = TASK_TYPE_ACCEPT_FRIENDS_REQUESTS,
                historyText = historyText,
                currentDate = currentDate,
                historyTime = dateUseCase.getCurrentDateTime(),
                userId = userId,
                threadNumber = 1
            )
        )
    }

    private suspend fun sendMessage(friendId: Int) {
        val hours = Calendar.getInstance().time.time / 1000 / 60 / 60 / 60
        messageUseCase.sendMessage(friendId, hours.toInt(), "", "")
    }

    companion object {
        const val Progress = "Progress"
        const val TaskProgress = "TaskProgress"
    }

}