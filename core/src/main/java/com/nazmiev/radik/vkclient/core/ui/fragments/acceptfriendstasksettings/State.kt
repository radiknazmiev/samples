package com.nazmiev.radik.vkclient.core.ui.fragments.acceptfriendstasksettings

import com.nazmiev.radik.vkclient.core.http.models.User


sealed class State {
    object Loading : State()
    data class UsersSuccess(
        val users: List<User>,
        val isRepeat: Boolean,
        val repeatPeriod: Float,
        val isSendMessage: Boolean
    ) : State()
    data class Finish(val taskId: Int, val isRepeat: Boolean, val repeatPeriod: Long, val isSendMessage: Boolean): State()
}
