package com.nazmiev.radik.vkclient.core.db.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "vk_groups")
data class VkGroup(
    @ColumnInfo(name = "user_id")
    val userId: Int?,
    @ColumnInfo(name = "group_id")
    val groupId: Int?,
)
{
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "_id")
    var id: Int = 0
}
