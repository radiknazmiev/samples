package com.nazmiev.radik.vkclient.core.db.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "selected_account_selected_chats")
data class SelectedAccountSelectedChat(
    @ColumnInfo(name = "chat_id")
    val chatId: Int?,
    @ColumnInfo(name = "account")
    val account: Int?,
)
{
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "_id")
    var id: Int = 0
}
