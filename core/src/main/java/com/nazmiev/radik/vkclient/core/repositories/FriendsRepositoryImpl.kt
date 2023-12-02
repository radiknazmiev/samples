package com.nazmiev.radik.vkclient.core.repositories

import com.nazmiev.radik.vkclient.core.http.HttpService
import com.nazmiev.radik.vkclient.core.http.models.FriendAdd
import com.nazmiev.radik.vkclient.core.http.models.FriendRequests
import javax.inject.Inject
import javax.inject.Named

class FriendsRepositoryImpl @Inject constructor() :
    FriendsRepository {

    override lateinit var httpService: HttpService

    override suspend fun getRequests(options: Map<String, String>): FriendRequests {
        return httpService.getRequests(options)
    }

    override suspend fun addFriend(options: Map<String, String>): FriendAdd {
        return httpService.addFriends(options)
    }


}