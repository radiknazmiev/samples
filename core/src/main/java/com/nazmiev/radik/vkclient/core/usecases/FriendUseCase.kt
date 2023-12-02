package com.nazmiev.radik.vkclient.core.usecases

import com.nazmiev.radik.vkclient.core.http.HttpService
import com.nazmiev.radik.vkclient.core.repositories.FriendsRepositoryImpl
import com.nazmiev.radik.vkclient.core.http.models.FriendAdd
import com.nazmiev.radik.vkclient.core.http.models.FriendRequests

interface FriendUseCase {

    fun initRepository(httpService: HttpService)

    suspend fun getOutRequests(
        accessToken: String,
        isNeedViewed: Boolean,
        offset: Int = 0,
        count: Int = 1000
    ): FriendRequests

    suspend fun getIncomingRequests(
        accessToken: String,
        isNeedViewed: Boolean,
        offset: Int = 0,
        count: Int = 1000
    ): FriendRequests

    suspend fun addFriend(
        accessToken: String,
        friendId: Int,
        text: String? = null
    ): FriendAdd
}
