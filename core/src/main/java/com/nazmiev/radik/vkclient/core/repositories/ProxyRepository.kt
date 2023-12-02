package com.nazmiev.radik.vkclient.core.repositories

import com.nazmiev.radik.vkclient.core.db.models.ProxyList

interface ProxyRepository {

    suspend fun getBoundedProxy(userId: Int): ProxyList?
}