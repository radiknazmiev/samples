package com.nazmiev.radik.vkclient.core.db.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "response_message")
data class ResponseMessage(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "_id")
    val id: Int,
    @ColumnInfo(name = "incoming_phrase")
    val incomingPhrase: String?,
    @ColumnInfo(name = "message_id")
    val messageId: Int?,
    @ColumnInfo(name = "previous_message")
    val previousMessage: Int?,
    @ColumnInfo(name = "processed_text")
    val processedText: String?,
    @ColumnInfo(name = "match_percentage")
    val matchPercentage: Int?,
)
