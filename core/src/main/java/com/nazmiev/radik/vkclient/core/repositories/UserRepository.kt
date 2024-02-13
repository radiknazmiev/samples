package com.nazmiev.radik.vkclient.core.repositories

import com.nazmiev.radik.vkclient.core.http.models.User
import com.nazmiev.radik.vkclient.core.http.models.UserResponse

interface UserRepository {

    suspend fun getUsersInfo(
        accessToken: String,
        userIds: List<String>,
        fields: List<String>
    ): List<User>

    suspend fun getAllUsers(): List<com.nazmiev.radik.vkclient.core.db.models.User>

    suspend fun getProfileInfo(accessTokens: List<String>): List<User>

    suspend fun checkUser(accessToken: String): UserResponse
}