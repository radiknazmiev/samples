package com.nazmiev.radik.vkclient.core.db.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "selected_accounts_for_task")
data class SelectedAccountForTask(
    @ColumnInfo(name = "task_id") val taskId: Int,
    @ColumnInfo(name = "account") val account: Int
)
{
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "_id")
    var id: Int = 0
}
