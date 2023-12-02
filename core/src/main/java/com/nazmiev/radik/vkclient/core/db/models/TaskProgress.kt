package com.nazmiev.radik.vkclient.core.db.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "task_progress")
data class TaskProgress(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "_id")
    val id: Int,
    @ColumnInfo(name = "type")
    val type: Int?,
    @ColumnInfo(name = "account_number")
    val accountNumber: Int?,
    @ColumnInfo(name = "current_requests_count")
    val currentRequestsCount: Int?,
    @ColumnInfo(name = "last_date")
    val lastDate: String?,
    @ColumnInfo(name = "is_full")
    val isFull: Int?,
    @ColumnInfo(name = "is_payed")
    val isPayed: Int?,
    @ColumnInfo(name = "thread_number")
    val threadNumber: Int?,
    @ColumnInfo(name = "accaunt")
    val account: Int?,
)
