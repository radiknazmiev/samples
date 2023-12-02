package com.nazmiev.radik.vkclient.core.db.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "messages_attachments")
data class MessagesAttachments(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "_id")
    val id: Int,
    @ColumnInfo(name = "message_id")
    val messageId: Int?,
    @ColumnInfo(name = "attach_id")
    val attachId: Int?,
    @ColumnInfo(name = "attach_owner_id")
    val attachOwnerId: Int?,
    @ColumnInfo(name = "attach_src")
    val attachSrc: String?,
    @ColumnInfo(name = "access_key")
    val accessKey: String?,
    @ColumnInfo(name = "attach_type")
    val attachType: String?,
    @ColumnInfo(name = "cover_url")
    val coverUrl: String?,
    @ColumnInfo(name = "file_path")
    val filePath: String?,
)
