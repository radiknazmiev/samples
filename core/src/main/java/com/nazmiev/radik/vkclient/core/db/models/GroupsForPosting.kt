package com.nazmiev.radik.vkclient.core.db.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "groups_for_posting")
data class GroupsForPosting(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "_id")
    val id: Int,
    @ColumnInfo(name = "pid")
    val pid: Int,
    @ColumnInfo(name = "group_id")
    val groupId: Int,
)
