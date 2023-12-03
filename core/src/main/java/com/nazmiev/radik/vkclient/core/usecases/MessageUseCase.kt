package com.nazmiev.radik.vkclient.core.usecases

import com.nazmiev.radik.vkclient.core.http.HttpService

interface MessageUseCase {

    fun initProxiedRepository(httpService: HttpService)

    suspend fun sendMessage(userId: Int, randomId: Int, message: String, attachment: String)

    suspend fun getWelcomeMessage()

}