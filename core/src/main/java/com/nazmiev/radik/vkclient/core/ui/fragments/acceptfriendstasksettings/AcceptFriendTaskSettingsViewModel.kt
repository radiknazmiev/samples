package com.nazmiev.radik.vkclient.core.ui.fragments.acceptfriendstasksettings

import android.content.SharedPreferences
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.core.content.edit
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nazmiev.radik.vkclient.core.Constants.TASK_TYPE_ACCEPT_FRIENDS_REQUESTS
import com.nazmiev.radik.vkclient.core.RequestFields.HAS_PHOTO
import com.nazmiev.radik.vkclient.core.RequestFields.PHOTO_100
import com.nazmiev.radik.vkclient.core.RequestFields.PHOTO_200
import com.nazmiev.radik.vkclient.core.RequestFields.PHOTO_50
import com.nazmiev.radik.vkclient.core.http.models.User
import com.nazmiev.radik.vkclient.core.usecases.DateUseCase
import com.nazmiev.radik.vkclient.core.usecases.LocalUserUseCase
import com.nazmiev.radik.vkclient.core.usecases.RemoteUserUseCase
import com.nazmiev.radik.vkclient.core.usecases.TaskUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.util.concurrent.TimeUnit
import javax.inject.Inject

@HiltViewModel
class AcceptFriendTaskSettingsViewModel @Inject constructor(
    private val localUserUseCase: LocalUserUseCase,
    private val remoteUserUseCase: RemoteUserUseCase,
    private val taskUseCase: TaskUseCase
) : ViewModel() {
    @Inject
    lateinit var sharedPreferences: SharedPreferences

    @Inject
    lateinit var dateUseCase: DateUseCase

    private val _state = MutableStateFlow<State>(State.Loading)
    private var users: SnapshotStateList<User> = mutableStateListOf()

    val state = _state.asStateFlow()

    init {
        getAllUsers()
    }

    private fun getAllUsers() {
        viewModelScope.launch {
            val localUsers = localUserUseCase.getAllUsers()
            val accessToken = localUsers[0].accessToken

            val userIds = mutableListOf<String>()
            localUsers.forEach { user ->
                user.login?.let { userIds.add(it) }
            }

            val fields = listOf(PHOTO_50, PHOTO_100, PHOTO_200, HAS_PHOTO)

            if (accessToken != null) {
                users.addAll(remoteUserUseCase.getUsersInfo(accessToken, userIds, fields))
                _state.value = State.UsersSuccess(
                    users, sharedPreferences.getBoolean(
                        ACCEPT_FRIENDS_TASK_IS_REPEAT, false
                    ), sharedPreferences.getFloat(
                        ACCEPT_FRIENDS_TASK_REPEAT_PERIOD, 0F
                    ),
                    sharedPreferences.getBoolean(
                        ACCEPT_FRIENDS_TASK_SEND_MESSAGE, false
                    )
                )
            }
        }
    }

    fun checkAccount(user: User) {
        val index = users.indexOf(user)
        val item = users[index]
        val isChecked = user.isChecked
        users[index] = item.copy(isChecked = !isChecked)

        _state.value = State.UsersSuccess(
            users, sharedPreferences.getBoolean(
                ACCEPT_FRIENDS_TASK_IS_REPEAT, false
            ), sharedPreferences.getFloat(
                ACCEPT_FRIENDS_TASK_REPEAT_PERIOD, 0F
            ),
            sharedPreferences.getBoolean(
                ACCEPT_FRIENDS_TASK_SEND_MESSAGE, false
            )
        )
    }

    fun saveRepeatTask(isRepeat: Boolean) {
        sharedPreferences.edit {
            putBoolean(ACCEPT_FRIENDS_TASK_IS_REPEAT, isRepeat)
        }

        _state.value = State.UsersSuccess(
            users, isRepeat, sharedPreferences.getFloat(
                ACCEPT_FRIENDS_TASK_REPEAT_PERIOD, 0F
            ),
            sharedPreferences.getBoolean(
                ACCEPT_FRIENDS_TASK_SEND_MESSAGE, false
            )
        )
    }

    fun saveRepeatPeriod(period: Float) {
        sharedPreferences.edit {
            putFloat(ACCEPT_FRIENDS_TASK_REPEAT_PERIOD, period)
        }

        _state.value = State.UsersSuccess(
            users, sharedPreferences.getBoolean(
                ACCEPT_FRIENDS_TASK_IS_REPEAT, false
            ), period,
            sharedPreferences.getBoolean(
                ACCEPT_FRIENDS_TASK_SEND_MESSAGE, false
            )
        )
    }

    fun saveSendMessage(isSend: Boolean) {
        sharedPreferences.edit {
            putBoolean(ACCEPT_FRIENDS_TASK_SEND_MESSAGE, isSend)
        }

        _state.value = State.UsersSuccess(
            users, sharedPreferences.getBoolean(
                ACCEPT_FRIENDS_TASK_IS_REPEAT, false
            ), sharedPreferences.getFloat(
                ACCEPT_FRIENDS_TASK_REPEAT_PERIOD, 0F
            ),
            isSend
        )
    }

    fun saveSelectedAccounts(taskName: String) {
        viewModelScope.launch {
            val taskId = taskUseCase.addNewTask(
                TASK_TYPE_ACCEPT_FRIENDS_REQUESTS,
                taskName,
                dateUseCase.getCurrentDateTime()
            )
            taskUseCase.addUsersToTask(
                taskId.toInt(),
                users.filter { it.isChecked }.map { it.id!! }.toList()
            )

            _state.value = State.Finish(
                taskId.toInt(),
                sharedPreferences.getBoolean(ACCEPT_FRIENDS_TASK_IS_REPEAT, false),
                TimeUnit.DAYS.toMillis(sharedPreferences.getFloat(ACCEPT_FRIENDS_TASK_REPEAT_PERIOD, 0F)
                    .toLong())
            )
        }
    }

    companion object {
        const val ACCEPT_FRIENDS_TASK_IS_REPEAT = "accept_friends_task_is_repeat"
        const val ACCEPT_FRIENDS_TASK_REPEAT_PERIOD = "accept_friends_task_repeat_period"
        const val ACCEPT_FRIENDS_TASK_SEND_MESSAGE = "accept_friends_task_send_message"
    }
}