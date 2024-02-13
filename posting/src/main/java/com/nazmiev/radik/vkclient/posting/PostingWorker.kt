package com.nazmiev.radik.vkclient.posting

import android.content.Context
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.nazmiev.radik.vkclient.core.db.models.GroupSearchSetting
import com.nazmiev.radik.vkclient.core.db.models.MessageWithAttachments
import com.nazmiev.radik.vkclient.core.ui.activitys.TaskSettingsActivity
import com.nazmiev.radik.vkclient.core.usecases.GroupSearchSettingUseCase
import com.nazmiev.radik.vkclient.core.usecases.GroupsForProcessingUseCase
import com.nazmiev.radik.vkclient.core.usecases.SharedPreferencesUseCase
import com.nazmiev.radik.vkclient.core.usecases.TaskUseCase
import com.nazmiev.radik.vkclient.core.usecases.UserUseCase
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import java.util.concurrent.atomic.AtomicInteger

@HiltWorker
class PostingWorker @AssistedInject constructor(
    @Assisted private val appContext: Context,
    @Assisted private val params: WorkerParameters,
    private val groupSearchSettingUseCase: GroupSearchSettingUseCase,
    private val groupsForProcessingUseCase: GroupsForProcessingUseCase,
    private val taskUseCase: TaskUseCase,
    private val userUseCase: UserUseCase,
    private val sharedPreferencesUseCase: SharedPreferencesUseCase
) : CoroutineWorker(appContext, params) {

    private var accountNumber: AtomicInteger = AtomicInteger(0)
    private var progress: AtomicInteger = AtomicInteger(0)
    private var taskId: Int? = null
    private var isRepeat: Boolean = false
    private var repeatPeriod: Long = 0
    private lateinit var groupSearchSetting: GroupSearchSetting
    private var groupsCount = 1000
    private var offset = 0
    private val groups: MutableSet<Int> = hashSetOf()
    private var responseCount = 0

    override suspend fun doWork(): Result {
        taskId = inputData.getInt(TaskSettingsActivity.FIELD_TASK_ID, 0)
        isRepeat = inputData.getBoolean(TaskSettingsActivity.FIELD_TASK_IS_REPEAT, false)
        repeatPeriod = inputData.getLong(TaskSettingsActivity.FIELD_TASK_REPEAT_PERIOD, 0)

        val taskUsers = taskUseCase.getTaskUsers(taskId!!).map {
            it.accessToken!!
        }
        val defaultUser = userUseCase.getFirstNotBlockedUser(accessTokens = taskUsers)

        val settingId = inputData.getInt(TaskSettingsActivity.FIELD_TASK_GROUP_SEARCH_SETTING_ID, 0)
        groupSearchSetting = groupSearchSettingUseCase.getSetting(settingId)

        when (groupSearchSetting.searchSource) {
            0 -> {
                searchGroups(defaultUser.accessToken)
            }
            2 -> {
                groups.addAll(groupsForProcessingUseCase.getGroups(groupSearchSetting.id).map { it.groupId })
            }
            else -> {
                groups.addAll(groupsForProcessingUseCase.getGroupsFromFile(groupSearchSetting.id).map { it.groupId })
            }
        }

        if (sharedPreferencesUseCase.isUseMultiThreading()) {

            coroutineScope {
                taskUsers.map { user ->
                    async {
                        delay(1000)
                        posting(user)
                    }
                }.awaitAll()
            }

        } else {
            taskUsers.forEach { user ->
                posting(user)
            }
        }


        return Result.success()
    }

    private suspend fun posting(accessToken: String) {

    }

    private suspend fun searchGroups(accessToken: String) {
        val response = groupsForProcessingUseCase.searchGroups(
            searchQuery = groupSearchSetting.phrase!!,
            accessToken = accessToken,
            groupType = groupSearchSetting.groupType!!,
            cityId = groupSearchSetting.city!!,
            sort = groupSearchSetting.sort!!,
            count = groupsCount,
            offset = offset
        )

        if (response.error == null) {
            responseCount = response.response?.count!!
            offset = response.response?.count!!
            response.response?.items?.filter { it.isClosed == 0 }?.map { it.id }?.let { groups.addAll(it as Collection<Int>) }
        }
    }
}