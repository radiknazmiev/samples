package com.nazmiev.radik.vkclient.core.db.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "planning_post_settings")
data class PlanningPostSetting(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "_id")
    val id: Int,
    @ColumnInfo(name = "post_id")
    val postId: Int?,
    @ColumnInfo(name = "user_id")
    val userId: Int?,
    @ColumnInfo(name = "user_name")
    val userName: String?,
    @ColumnInfo(name = "planning_date_time")
    val planningDateTime: String?,
    @ColumnInfo(name = "repost")
    val repost: Int?,
    @ColumnInfo(name = "set_like")
    val setLike: Int?,
    @ColumnInfo(name = "planning_post_message")
    val planningPostMessage: String?,
    @ColumnInfo(name = "set_comment")
    val setComment: Int?,
    @ColumnInfo(name = "comment_pause")
    val commentPause: Int?,
    @ColumnInfo(name = "post_on_telegram")
    val postOnTelegram: Int?,
    @ColumnInfo(name = "telegram_chats")
    val telegramChats: String?,
)
