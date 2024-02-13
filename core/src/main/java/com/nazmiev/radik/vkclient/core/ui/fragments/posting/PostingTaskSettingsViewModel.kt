package com.nazmiev.radik.vkclient.core.ui.fragments.posting

import android.content.SharedPreferences
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.core.content.edit
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nazmiev.radik.vkclient.core.db.models.GroupSearchSetting
import com.nazmiev.radik.vkclient.core.http.models.User
import com.nazmiev.radik.vkclient.core.usecases.DateUseCase
import com.nazmiev.radik.vkclient.core.usecases.GroupSearchSettingUseCase
import com.nazmiev.radik.vkclient.core.usecases.RemoteUserUseCase
import com.nazmiev.radik.vkclient.core.usecases.TaskUseCase
import com.nazmiev.radik.vkclient.core.usecases.UserUseCase
import com.nazmiev.radik.vkclient.core.utils.Constants
import com.nazmiev.radik.vkclient.core.utils.RequestFields
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.util.concurrent.TimeUnit
import javax.inject.Inject

@HiltViewModel
class PostingTaskSettingsViewModel  @Inject constructor(
    private val userUseCase: UserUseCase,
    private val remoteUserUseCase: RemoteUserUseCase,
    private val taskUseCase: TaskUseCase,
    groupSearchSettingUseCase: GroupSearchSettingUseCase
) : ViewModel() {

    @Inject
    lateinit var sharedPreferences: SharedPreferences

    @Inject
    lateinit var dateUseCase: DateUseCase

    private val _state = MutableStateFlow<State>(State.Loading)
    private var users: SnapshotStateList<User> = mutableStateListOf()

    lateinit var postingSettings: List<GroupSearchSetting>

    val state = _state.asStateFlow()

    init {
        getAllUsers()
        viewModelScope.launch(Dispatchers.IO) {
            postingSettings = groupSearchSettingUseCase.getAllSettings()
        }
    }

    private fun getAllUsers() {
        viewModelScope.launch {
            val localUsers = userUseCase.getAllUsers()
            val accessToken = localUsers[0].accessToken

            val userIds = mutableListOf<String>()
            localUsers.forEach { user ->
                user.login?.let { userIds.add(it) }
            }

            val fields = listOf(
                RequestFields.PHOTO_50,
                RequestFields.PHOTO_100,
                RequestFields.PHOTO_200,
                RequestFields.HAS_PHOTO
            )

            if (accessToken != null) {
                users.addAll(remoteUserUseCase.getUsersInfo(accessToken, userIds, fields))
                users.map { remoteUser ->
                    remoteUser.note = localUsers.first { it.login == remoteUser.id.toString() }.accountTitle?: ""
                }
                _state.value = State.UsersSuccess(
                    users, sharedPreferences.getBoolean(
                        POSTING_TASK_IS_REPEAT, false
                    ), sharedPreferences.getFloat(
                        POSTING_TASK_REPEAT_PERIOD, 0F
                    )
                )
            }
        }
    }

    fun saveSelectedSettingId(id: Int) {
        sharedPreferences.edit {
            putInt(POSTING_TASK_SELECTED_SETTING, id)
        }
    }

    fun getSelectedSettingId(): Int {
        return sharedPreferences.getInt(POSTING_TASK_SELECTED_SETTING, 0)
    }

    fun saveRepeatTask(isRepeat: Boolean) {
        sharedPreferences.edit {
            putBoolean(POSTING_TASK_IS_REPEAT, isRepeat)
        }

        _state.value = State.UsersSuccess(
            users, isRepeat, sharedPreferences.getFloat(
                POSTING_TASK_REPEAT_PERIOD, 0F
            )
        )
    }

    fun saveRepeatPeriod(period: Float) {
        sharedPreferences.edit {
            putFloat(POSTING_TASK_REPEAT_PERIOD, period)
        }

        _state.value = State.UsersSuccess(
            users, sharedPreferences.getBoolean(
                POSTING_TASK_IS_REPEAT, false
            ), period
        )
    }

    fun checkAccount(user: User) {
        val index = users.indexOf(user)
        val item = users[index]
        val isChecked = user.isChecked
        users[index] = item.copy(isChecked = !isChecked)

        _state.value = State.UsersSuccess(
            users, sharedPreferences.getBoolean(
                POSTING_TASK_IS_REPEAT, false
            ), sharedPreferences.getFloat(
                POSTING_TASK_REPEAT_PERIOD, 0F
            )
        )
    }

    fun saveSelectedAccounts(taskName: String) {
        viewModelScope.launch {
            val taskId = taskUseCase.addNewTask(
                Constants.TASK_TYPE_POSTING,
                taskName,
                dateUseCase.getCurrentDateTime()
            )
            taskUseCase.addUsersToTask(
                taskId.toInt(),
                users.filter { it.isChecked }.map { it.id!! }.toList()
            )

            _state.value = State.Finish(
                taskId.toInt(),
                sharedPreferences.getBoolean(POSTING_TASK_IS_REPEAT, false),
                TimeUnit.DAYS.toMillis(
                    sharedPreferences.getFloat(POSTING_TASK_REPEAT_PERIOD, 0F)
                        .toLong()
                )
            )
        }
    }

    companion object {
        const val POSTING_TASK_IS_REPEAT = "posting_task_is_repeat"
        const val POSTING_TASK_REPEAT_PERIOD = "posting_task_repeat_period"
        const val POSTING_TASK_SELECTED_SETTING = "posting_task_selected_setting"
    }
}