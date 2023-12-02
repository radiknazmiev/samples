package com.nazmiev.radik.vkclient.core.db.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(tableName = "tasks", indices = [Index(value = ["type"], unique = true)])
data class Task(
    @ColumnInfo(name = "name")
    val name: String?,
    @ColumnInfo(name = "type")
    val type: Int?,
    @ColumnInfo(name = "scheduled_start_time")
    val scheduledStartTime: String?,
    @ColumnInfo(name = "status")
    val status: Int?,
) {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "_id")
    var id: Int = 0
}