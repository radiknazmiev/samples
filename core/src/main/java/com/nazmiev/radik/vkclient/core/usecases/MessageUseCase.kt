package com.nazmiev.radik.vkclient.core.usecases

import com.nazmiev.radik.vkclient.core.db.models.MessageWithAttachments
import com.nazmiev.radik.vkclient.core.db.models.MessagesAttachments
import com.nazmiev.radik.vkclient.core.http.HttpService
import com.nazmiev.radik.vkclient.core.http.models.MessageResponse

interface MessageUseCase {

    fun initProxiedRepository(httpService: HttpService)

    suspend fun sendMessage(
        userId: Int,
        randomId: Int,
        message: String,
        attachment: String,
        captchaKey: String? = null,
        captchaSid: String? = null,
        accessToken: String
    ): MessageResponse

    suspend fun getWelcomeMessage(userId: Int): List<MessageWithAttachments>

    fun getMessageAttachmentsString(messagesAttachments: List<MessagesAttachments>): String

}