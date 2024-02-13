package com.nazmiev.radik.vkclient.core.db.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "groups_for_posting")
data class GroupsForPosting(
    @ColumnInfo(name = "pid")
    val pid: Int,
    @ColumnInfo(name = "group_id")
    val groupId: Int,
)
{
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "_id")
    var id: Int = 0
}
