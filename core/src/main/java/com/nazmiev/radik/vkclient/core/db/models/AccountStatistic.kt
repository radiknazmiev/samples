package com.nazmiev.radik.vkclient.core.db.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "account_statistic")
data class AccountStatistic(
    @ColumnInfo(name = "static_date")
    val staticDate: String,
    @ColumnInfo(name = "account_id")
    val accountId: Int,
    @ColumnInfo(name = "fr_request_count", defaultValue = "0")
    val friendRequestCount: Int,
    @ColumnInfo(name = "messages_count", defaultValue = "0")
    val messagesCount: Int,
    @ColumnInfo(name = "likes_count", defaultValue = "0")
    val likesCount: Int,
    @ColumnInfo(name = "posts_count", defaultValue = "0")
    val postsCount: Int,
    @ColumnInfo(name = "unfollow_count", defaultValue = "0")
    val unfollowCount: Int,
)
{
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "_id")
    var id: Int = 0
}
