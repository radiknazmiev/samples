package com.nazmiev.radik.vkclient.core.http

import com.nazmiev.radik.vkclient.core.http.models.FriendAdd
import com.nazmiev.radik.vkclient.core.http.models.FriendRequests
import com.nazmiev.radik.vkclient.core.http.models.UserResponse
import retrofit2.http.GET
import retrofit2.http.QueryMap

interface HttpService {

    @GET("friends.getRequests")
    suspend fun getRequests(@QueryMap options: Map<String, String>): FriendRequests

    @GET("friends.add")
    suspend fun addFriends(@QueryMap options: Map<String, String>): FriendAdd

    @GET("users.get")
    suspend fun getUsersInfo(@QueryMap options: Map<String, String>): UserResponse

}