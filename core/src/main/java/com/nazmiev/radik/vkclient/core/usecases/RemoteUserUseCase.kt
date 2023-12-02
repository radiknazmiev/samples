package com.nazmiev.radik.vkclient.core.usecases

import com.nazmiev.radik.vkclient.core.http.models.User

interface RemoteUserUseCase {

    suspend fun getUsersInfo(
        accessToken: String,
        userIds: List<String>,
        fields: List<String>
    ): List<User>
}