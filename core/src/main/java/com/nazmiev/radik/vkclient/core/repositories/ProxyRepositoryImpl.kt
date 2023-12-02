package com.nazmiev.radik.vkclient.core.repositories

import com.nazmiev.radik.vkclient.core.db.AppDatabase
import com.nazmiev.radik.vkclient.core.db.models.ProxyList
import javax.inject.Inject

class ProxyRepositoryImpl @Inject constructor(private val db: AppDatabase): ProxyRepository {

    override suspend fun getBoundedProxy(userId: Int): ProxyList? {
        return db.proxyAccountDao().getBoundedProxy(userId)
    }
}