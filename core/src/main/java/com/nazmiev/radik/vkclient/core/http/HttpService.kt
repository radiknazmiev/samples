package com.nazmiev.radik.vkclient.core.http

import com.nazmiev.radik.vkclient.core.http.models.FriendAdd
import com.nazmiev.radik.vkclient.core.http.models.FriendRequests
import com.nazmiev.radik.vkclient.core.http.models.GroupResponse
import com.nazmiev.radik.vkclient.core.http.models.MessageResponse
import com.nazmiev.radik.vkclient.core.http.models.ProfileInfoResponse
import com.nazmiev.radik.vkclient.core.http.models.UserResponse
import okhttp3.RequestBody
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part
import retrofit2.http.QueryMap

interface HttpService {

    @GET("friends.getRequests")
    suspend fun getRequests(@QueryMap options: Map<String, String>): FriendRequests

    @GET("friends.add")
    suspend fun addFriends(@QueryMap options: Map<String, String>): FriendAdd

    @GET("users.get")
    suspend fun getUsersInfo(@QueryMap options: Map<String, String>): UserResponse

    @Multipart
    @POST("messages.send")
    suspend fun sendMessage(
        @Part("user_id") userId: RequestBody,
        @Part("random_id") randomId: RequestBody,
        @Part("message") message: RequestBody,
        @Part("attachment") attachment: RequestBody?,
        @Part("captcha_key") captchaKey: RequestBody?,
        @Part("captcha_sid") captchaSid: RequestBody?,
        @Part("access_token") accessToken: RequestBody?,
        @Part("v") apiVersion: RequestBody?,
    ): MessageResponse

    @Multipart
    @POST("execute")
    suspend fun getProfileInfo(
        @Part("code") code: RequestBody,
        @Part("access_token") accessToken: RequestBody?,
        @Part("v") apiVersion: RequestBody?,
    ): ProfileInfoResponse

    @GET("groups.search")
    suspend fun searchGroups(@QueryMap options: Map<String, String>): GroupResponse

    @Multipart
    @POST("wall.post")
    suspend fun wallPost(
        @Part("owner_id") ownerId: RequestBody,
        @Part("message") message: RequestBody,
        @Part("attachment") attachment: RequestBody?,
        @Part("captcha_key") captchaKey: RequestBody?,
        @Part("captcha_sid") captchaSid: RequestBody?,
        @Part("access_token") accessToken: RequestBody?,
        @Part("v") apiVersion: RequestBody?,
        @Part("guid") guid: RequestBody?,
    )

    @Multipart
    @POST("wall.repost")
    suspend fun wallRepost(
        @Part("group_id") groupId: RequestBody,
        @Part("message") message: RequestBody,
        @Part("object") attachment: RequestBody?,
        @Part("captcha_key") captchaKey: RequestBody?,
        @Part("captcha_sid") captchaSid: RequestBody?,
        @Part("access_token") accessToken: RequestBody?,
        @Part("v") apiVersion: RequestBody?,
    )

}