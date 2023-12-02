package com.nazmiev.radik.vkclient.core.db.dao

import androidx.room.Dao
import androidx.room.Query
import com.nazmiev.radik.vkclient.core.db.models.ProxyList

@Dao
interface ProxyAccountDao {

    @Query("select proxy_list.* from proxy_accaunt " +
            "INNER join proxy_list on proxy_list._id =  proxy_accaunt.proxy " +
            "where proxy_accaunt.accaunt = :userId")
    suspend fun getBoundedProxy(userId: Int): ProxyList?

}