package com.nazmiev.radik.vkclient.core.db.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "group_chat_message")
data class GroupChatMessage(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "_id")
    val id: Int,
    @ColumnInfo(name = "user_id")
    val userId: Int,
    @ColumnInfo(name = "message_id")
    val messageId: Int

)
