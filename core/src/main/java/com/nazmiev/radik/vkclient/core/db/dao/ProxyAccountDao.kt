package com.nazmiev.radik.vkclient.core.db.dao

import androidx.room.Dao
import androidx.room.Query
import com.nazmiev.radik.vkclient.core.db.models.ProxyList

@Dao
interface ProxyAccountDao {

    @Query("select proxy_list.* from proxy_account " +
            "INNER join proxy_list on proxy_list._id =  proxy_account.proxy " +
            "where proxy_account.account = :userId")
    suspend fun getBoundedProxy(userId: Int): ProxyList?

}