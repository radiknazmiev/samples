package com.nazmiev.radik.vkclient.core.usecases

import com.nazmiev.radik.vkclient.core.db.models.ProxyList
import com.nazmiev.radik.vkclient.core.http.HttpService
import com.nazmiev.radik.vkclient.core.repositories.FriendsRepository
import com.nazmiev.radik.vkclient.core.repositories.FriendsRepositoryImpl

interface ProxyUseCase {

    suspend fun getBoundedProxy(userId: Int): ProxyList?

    suspend fun getProxyHttpClient(userId: Int): HttpService
}