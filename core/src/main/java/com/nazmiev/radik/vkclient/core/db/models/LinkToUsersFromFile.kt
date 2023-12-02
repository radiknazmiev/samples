package com.nazmiev.radik.vkclient.core.db.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "link_to_users_from_file")
data class LinkToUsersFromFile(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "_id")
    val id: Int,
    @ColumnInfo(name = "user_id")
    val userId: Int?,
    @ColumnInfo(name = "vk_user_id")
    val vkUserId: Int?,
)
