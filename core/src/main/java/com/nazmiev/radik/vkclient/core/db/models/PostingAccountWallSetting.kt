package com.nazmiev.radik.vkclient.core.db.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "posting_account_wall_settings")
data class PostingAccountWallSetting(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "_id")
    val id: Int,
    @ColumnInfo(name = "script_id")
    val scriptId: Int?,
    @ColumnInfo(name = "post_id")
    val postId: Int?,
)