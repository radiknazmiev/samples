package com.nazmiev.radik.vkclient.core.repositories

import com.nazmiev.radik.vkclient.core.db.AppDatabase
import com.nazmiev.radik.vkclient.core.db.models.MessageWithAttachments
import com.nazmiev.radik.vkclient.core.http.HttpService
import com.nazmiev.radik.vkclient.core.http.models.MessageBody
import com.nazmiev.radik.vkclient.core.http.models.MessageResponse
import com.nazmiev.radik.vkclient.core.utils.Constants
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody.Companion.toRequestBody
import javax.inject.Inject

class MessageRepositoryImpl @Inject constructor(
    private val db: AppDatabase
) : MessagesRepository {

    override lateinit var httpService: HttpService

    override suspend fun sendMessage(
        userId: Int,
        randomId: Int,
        message: String,
        attachment: String,
        captchaKey: String?,
        captchaSid: String?,
        accessToken: String
    ): MessageResponse {
        return httpService.sendMessage(
            userId.toString().toRequestBody("text/plain".toMediaTypeOrNull()),
            randomId.toString().toRequestBody("text/plain".toMediaTypeOrNull()),
            message.toRequestBody("text/plain".toMediaTypeOrNull()),
            attachment.toRequestBody("text/plain".toMediaTypeOrNull()),
            captchaKey?.toRequestBody("text/plain".toMediaTypeOrNull()),
            captchaSid?.toRequestBody("text/plain".toMediaTypeOrNull()),
            accessToken.toRequestBody("text/plain".toMediaTypeOrNull()),
            Constants.VK_API_VERSION.toRequestBody("text/plain".toMediaTypeOrNull())
        )
    }

    override suspend fun getWelcomeMessage(userId: Int): List<MessageWithAttachments> {
        return db.messageDao().getWelcomeMessageWithAttachments(userId)
    }
}