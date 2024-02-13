package com.nazmiev.radik.vkclient.core.db.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "group_search_setting")
data class GroupSearchSetting(
    @ColumnInfo(name = "user_id")
    val userId: Int?,
    @ColumnInfo(name = "search_source")
    val searchSource: Int?,
    @ColumnInfo(name = "country")
    val country: Int?,
    @ColumnInfo(name = "city")
    val city: Int?,
    @ColumnInfo(name = "phrase")
    val phrase: String?,
    @ColumnInfo(name = "sort")
    val sort: Int?,
    @ColumnInfo(name = "group_count_from")
    val groupCountFrom: Int?,
    @ColumnInfo(name = "group_count_to")
    val groupCountTo: Int?,
    @ColumnInfo(name = "message_id")
    val messageId: Int?,
    @ColumnInfo(name = "group_type")
    val groupType: Int?,
    @ColumnInfo(name = "auto_liking_post")
    val autoLikingPost: Int?,
    @ColumnInfo(name = "filed_post")
    val filedPost: Int?,
    @ColumnInfo(name = "pre_join_group")
    val preJoinGroup: Int?,
    @ColumnInfo(name = "setting_title")
    val settingTitle: String?
)
{
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "_id")
    var id: Int = 0
}
