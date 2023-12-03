package com.nazmiev.radik.vkclient.core.usecases

import com.nazmiev.radik.vkclient.core.http.HttpService
import com.nazmiev.radik.vkclient.core.repositories.MessagesRepository
import javax.inject.Inject
import javax.inject.Named

class MessageUseCaseImpl @Inject constructor (
    private val messagesRepository: MessagesRepository,
    @Named("VKAPI") private val httpService: HttpService
): MessageUseCase {

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
        attachment: String
    ) {
        messagesRepository.sendMessage(userId, randomId, message, attachment)
    }

    override suspend fun getWelcomeMessage() {

    }
}