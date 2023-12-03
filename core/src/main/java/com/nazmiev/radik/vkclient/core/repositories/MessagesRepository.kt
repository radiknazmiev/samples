package com.nazmiev.radik.vkclient.core.repositories

import com.nazmiev.radik.vkclient.core.http.HttpService

interface MessagesRepository {

    var httpService: HttpService

    suspend fun sendMessage(userId: Int, randomId: Int, message: String, attachment: String)

    suspend fun getWelcomeMessage()
}