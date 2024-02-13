package com.nazmiev.radik.vkclient.core.db.models

import androidx.room.ColumnInfo
import androidx.room.Relation

data class MessageWithAttachments(
    @ColumnInfo("_id")
    var id: Int?,
    @ColumnInfo("user_id")
    var userId: Int?,
    @ColumnInfo("message_text")
    var messageText: String?,
    var title: String?,
    var type: Int?,

    @Relation(parentColumn = "_id", entityColumn = "message_id")
    var attachments: List<MessagesAttachments>
)
