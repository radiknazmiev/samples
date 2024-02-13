package com.nazmiev.radik.vkclient.core.db.dao

import androidx.room.Dao
import androidx.room.Query
import com.nazmiev.radik.vkclient.core.db.models.GroupsForProcessing

@Dao
interface GroupsForProcessingDao {

    @Query("select * from groups_for_processing where pid = :pid")
    suspend fun getGroups(pid: Int): List<GroupsForProcessing>
}