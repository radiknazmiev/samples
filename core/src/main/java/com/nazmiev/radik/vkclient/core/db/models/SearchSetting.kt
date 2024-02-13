package com.nazmiev.radik.vkclient.core.db.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "search_settings")
data class SearchSetting(
    @ColumnInfo(name = "account")
    val account: Int?,
    @ColumnInfo(name = "search_source")
    val searchSource: Int?,
    @ColumnInfo(name = "invite_to_group")
    val inviteToGroup: Int?,
    @ColumnInfo(name = "group_link")
    val groupLink: Int?,
    @ColumnInfo(name = "sent_message")
    val sentMessage: Int?,
    @ColumnInfo(name = "attraction_source")
    val attractionSource: Int?,
    @ColumnInfo(name = "sort")
    val sort: Int?,
)
{
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "_id")
    var id: Int = 0
}
