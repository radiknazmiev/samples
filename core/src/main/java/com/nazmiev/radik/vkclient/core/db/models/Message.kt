package com.nazmiev.radik.vkclient.core.db.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "messages")
data class Message(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "_id")
    val id: Int,
    @ColumnInfo(name = "user_id")
    val userId: Int?,
    @ColumnInfo(name = "message_text")
    val messageText: String?,
    @ColumnInfo(name = "title")
    val title: String?,
    @ColumnInfo(name = "type")
    val type: Int?,
)
