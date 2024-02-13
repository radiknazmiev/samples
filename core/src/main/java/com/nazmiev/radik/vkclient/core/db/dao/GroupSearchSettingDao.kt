package com.nazmiev.radik.vkclient.core.db.dao

import androidx.room.Dao
import androidx.room.Query
import com.nazmiev.radik.vkclient.core.db.models.GroupSearchSetting

@Dao
interface GroupSearchSettingDao {

    @Query("select * from group_search_setting where _id = :id")
    suspend fun getGroupSearchSetting(id: Int): GroupSearchSetting

    @Query("select * from group_search_setting")
    suspend fun getAllGroupSearchSettings(): List<GroupSearchSetting>
}