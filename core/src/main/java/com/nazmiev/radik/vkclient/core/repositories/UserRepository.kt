package com.nazmiev.radik.vkclient.core.repositories

import com.nazmiev.radik.vkclient.core.http.models.User

interface UserRepository {

    suspend fun getUsersInfo(
        accessToken: String,
        userIds: List<String>,
        fields: List<String>
    ): List<User>

    suspend fun getAllUsers(): List<com.nazmiev.radik.vkclient.core.db.models.User>
}