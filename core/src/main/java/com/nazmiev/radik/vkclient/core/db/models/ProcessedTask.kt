package com.nazmiev.radik.vkclient.core.db.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "processed_task")
data class ProcessedTask(
    @ColumnInfo(name = "task_id")
    val taskId: Int?,
    @ColumnInfo(name = "task_type")
    val taskType: Int?,
)
{
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "_id")
    var id: Int = 0
}
