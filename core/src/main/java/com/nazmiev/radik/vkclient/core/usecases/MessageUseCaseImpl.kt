package com.nazmiev.radik.vkclient.core.usecases

import android.content.Context
import android.graphics.drawable.BitmapDrawable
import coil.ImageLoader
import coil.request.ImageRequest
import coil.request.SuccessResult
import com.nazmiev.radik.vkclient.core.db.models.MessageWithAttachments
import com.nazmiev.radik.vkclient.core.db.models.MessagesAttachments
import com.nazmiev.radik.vkclient.core.http.HttpService
import com.nazmiev.radik.vkclient.core.http.models.MessageResponse
import com.nazmiev.radik.vkclient.core.repositories.MessagesRepository
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.delay
import javax.inject.Inject
import javax.inject.Named

class MessageUseCaseImpl @Inject constructor(
    private val messagesRepository: MessagesRepository,
    @Named("VKAPI") private val httpService: HttpService,
    @ApplicationContext private val context: Context,
    private val captchaUseCase: CaptchaUseCase
) : MessageUseCase {

    init {
        messagesRepository.httpService = httpService
    }

    override fun initProxiedRepository(httpService: HttpService) {
        messagesRepository.httpService = httpService
    }

    override suspend fun sendMessage(
        userId: Int,
        randomId: Int,
        message: String,
        attachment: String,
        captchaKey: String?,
        captchaSid: String?,
        accessToken: String
    ): MessageResponse {

        val result = messagesRepository.sendMessage(
            userId,
            randomId,
            message,
            attachment,
            captchaKey,
            captchaSid,
            accessToken
        )

        if (result.error != null && result.error.errorCode == 14) {
            val captchaSid = result.error.captchaSid
            val captchaImg = result.error.captchaImg

            val loader = ImageLoader(context)
            val request = ImageRequest.Builder(context)
                .data(captchaImg)
                .allowHardware(false)
                .build()

            val loadResult = (loader.execute(request) as SuccessResult).drawable
            val bitmap = (loadResult as BitmapDrawable).bitmap

            val captchaResponse = captchaUseCase.setCaptcha(bitmap)

            if (captchaResponse.error == null) {
                delay(5000)
                val captchaResult =
                    captchaUseCase.getResolvedCaptcha(captchaResponse.captchaId!!)

                sendMessage(
                    userId,
                    randomId,
                    message,
                    attachment,
                    captchaKey = captchaResult,
                    captchaSid = captchaSid,
                    accessToken
                )
            }
        }

        return result
    }

    override suspend fun getWelcomeMessage(userId: Int): List<MessageWithAttachments> {
        return messagesRepository.getWelcomeMessage(userId)
    }

    override fun getMessageAttachmentsString(messagesAttachments: List<MessagesAttachments>): String {
        return messagesAttachments.joinToString(",") { attachments ->
            val key = if (attachments.accessKey == null) "" else "_" + attachments.accessKey
            (attachments.attachType + attachments.attachOwnerId + "_" + attachments.attachId + key)
        }
    }
}