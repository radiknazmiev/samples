package com.nazmiev.radik.vkclient.core.db.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "foaf")
data class Foaf(
    @ColumnInfo(name = "user_id")
    val userId: Int,
    @ColumnInfo(name = "create_date")
    val createDate: String,
    @ColumnInfo(name = "modified_date")
    val modifiedDate: String,
    @ColumnInfo(name = "last_logged_in")
    val lastLoggedIn: Int,
)
{
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "_id")
    var id: Int = 0
}