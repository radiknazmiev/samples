package com.nazmiev.radik.vkclient.core.usecases

import com.nazmiev.radik.vkclient.core.db.models.ProxyList
import com.nazmiev.radik.vkclient.core.http.HttpService
import com.nazmiev.radik.vkclient.core.http.ProxyHttpClient
import com.nazmiev.radik.vkclient.core.repositories.FriendsRepository
import com.nazmiev.radik.vkclient.core.repositories.FriendsRepositoryImpl
import com.nazmiev.radik.vkclient.core.repositories.ProxyRepository
import javax.inject.Inject

class ProxyUseCaseImpl @Inject constructor(
    private val proxyRepository: ProxyRepository,
    private val proxyHttpClient: ProxyHttpClient
): ProxyUseCase {

    override suspend fun getBoundedProxy(userId: Int): ProxyList? {
        return proxyRepository.getBoundedProxy(userId)
    }

    override suspend fun getProxyHttpClient(userId: Int): HttpService {
        return proxyHttpClient.initialization(userId)
    }
}