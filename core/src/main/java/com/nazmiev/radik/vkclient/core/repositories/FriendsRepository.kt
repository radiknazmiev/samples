package com.nazmiev.radik.vkclient.core.repositories

import com.nazmiev.radik.vkclient.core.http.HttpService
import com.nazmiev.radik.vkclient.core.http.models.FriendAdd
import com.nazmiev.radik.vkclient.core.http.models.FriendRequests

interface FriendsRepository {

    var httpService: HttpService

    suspend fun getRequests(options: Map<String, String>): FriendRequests

    suspend fun addFriend(options: Map<String, String>): FriendAdd
}