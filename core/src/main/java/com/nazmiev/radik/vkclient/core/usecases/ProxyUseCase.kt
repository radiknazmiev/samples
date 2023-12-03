package com.nazmiev.radik.vkclient.core.usecases

import com.nazmiev.radik.vkclient.core.db.models.ProxyList
import com.nazmiev.radik.vkclient.core.http.HttpService

interface ProxyUseCase {

    suspend fun getBoundedProxy(userId: Int): ProxyList?

    suspend fun getProxyHttpClient(userId: Int): HttpService
}