package com.nazmiev.radik.vkclient.core.db.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cookies")
data class Cookie(
    @ColumnInfo(name = "user_id")
    val userId: Int,
    @ColumnInfo(name = "user_agent")
    val userAgent: Int?,
    @ColumnInfo(name = "cookie_name")
    val cookieName: String,
    @ColumnInfo(name = "cookie_value")
    val cookieValue: String,
)
{
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "_id")
    var id: Int = 0
}
