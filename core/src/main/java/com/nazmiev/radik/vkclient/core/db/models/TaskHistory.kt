package com.nazmiev.radik.vkclient.core.db.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "task_history")
data class TaskHistory(
    @ColumnInfo(name = "task_type") val taskType: Int,
    @ColumnInfo(name = "history_text") val historyText: String,
    @ColumnInfo(name = "history_time") val historyTime: String,
    @ColumnInfo(name = "current_d") val currentDate: String,
    @ColumnInfo(name = "user_id") val userId: Int,
    @ColumnInfo(name = "thread_number") val threadNumber: Int
)
{
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}
