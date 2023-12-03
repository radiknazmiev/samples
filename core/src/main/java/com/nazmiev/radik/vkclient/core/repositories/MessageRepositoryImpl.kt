package com.nazmiev.radik.vkclient.core.repositories

import com.nazmiev.radik.vkclient.core.http.HttpService
import com.nazmiev.radik.vkclient.core.http.models.MessageBody
import javax.inject.Inject

class MessageRepositoryImpl @Inject constructor(): MessagesRepository {

    override lateinit var httpService: HttpService

    override suspend fun sendMessage(
        userId: Int,
        randomId: Int,
        message: String,
        attachment: String
    ) {
        httpService.sendMessage(MessageBody(userId, randomId, message, attachment))
    }

    override suspend fun getWelcomeMessage() {

    }
}