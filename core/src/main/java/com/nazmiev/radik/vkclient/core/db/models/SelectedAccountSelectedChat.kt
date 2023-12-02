package com.nazmiev.radik.vkclient.core.db.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "selected_account_selected_chats")
data class SelectedAccountSelectedChat(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "_id")
    val id: Int,
    @ColumnInfo(name = "chat_id")
    val chatId: Int?,
    @ColumnInfo(name = "accaunt")
    val account: Int?,
)
