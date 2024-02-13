package com.nazmiev.radik.vkclient.core.db.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_agents")
data class UserAgent (
    @ColumnInfo(name = "user_id")
    val userId: Int?,
    @ColumnInfo(name = "user_agent")
    val userAgent: String
)
{
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "_id")
    var id: Int = 0
}