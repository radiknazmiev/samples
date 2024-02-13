package com.nazmiev.radik.vkclient.core.db.dao

import androidx.room.Dao
import androidx.room.Query
import com.nazmiev.radik.vkclient.core.db.models.GroupsForPosting

@Dao
interface GroupsForPostingDao {

    @Query("select * from groups_for_posting where pid = :pid limit :limit")
    suspend fun getGroupsWithLimit(pid: Int, limit: Int): List<GroupsForPosting>

    @Query("select * from groups_for_posting where pid = :pid")
    suspend fun getGroups(pid: Int): List<GroupsForPosting>
}