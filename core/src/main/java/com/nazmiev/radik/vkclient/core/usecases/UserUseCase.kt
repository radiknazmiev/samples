package com.nazmiev.radik.vkclient.core.usecases

import com.nazmiev.radik.vkclient.core.db.models.User
import com.nazmiev.radik.vkclient.core.http.models.User as NetworkUser

interface UserUseCase {

    suspend fun getAllUsers(): List<User>

    suspend fun getUserInfo(
        accessToken: String,
        userIds: List<String>,
        fields: List<String>
    ): List<com.nazmiev.radik.vkclient.core.http.models.User>

    suspend fun getProfileInfo(accessTokens: List<String>): List<com.nazmiev.radik.vkclient.core.http.models.User>

    suspend fun getFirstNotBlockedUser(accessTokens: List<String>, count: Int = 0): NetworkUser
}