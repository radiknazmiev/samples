package com.nazmiev.radik.vkclient.core.repositories

import com.nazmiev.radik.vkclient.core.db.models.MessageWithAttachments
import com.nazmiev.radik.vkclient.core.http.HttpService
import com.nazmiev.radik.vkclient.core.http.models.MessageResponse

interface MessagesRepository {

    var httpService: HttpService

    suspend fun sendMessage(
        userId: Int,
        randomId: Int,
        message: String,
        attachment: String,
        captchaKey: String?,
        captchaSid: String?,
        accessToken: String
    ): MessageResponse

    suspend fun getWelcomeMessage(userId: Int): List<MessageWithAttachments>
}