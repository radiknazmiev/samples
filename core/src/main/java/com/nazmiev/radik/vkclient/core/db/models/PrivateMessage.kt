package com.nazmiev.radik.vkclient.core.db.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "private_message")
data class PrivateMessage(
    @ColumnInfo(name = "user_id")
    val userId: Int?,
    @ColumnInfo(name = "message_id")
    val messageId: Int?,
)
{
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "_id")
    var id: Int = 0
}
