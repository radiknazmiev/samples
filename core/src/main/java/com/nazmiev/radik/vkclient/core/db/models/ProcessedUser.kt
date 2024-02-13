package com.nazmiev.radik.vkclient.core.db.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "processed_users")
data class ProcessedUser(
    @ColumnInfo(name = "user_id")
    val userId: Int?,
    @ColumnInfo(name = "operation_type")
    val operationType: Int?,
)
{
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "_id")
    var id: Int = 0
}
