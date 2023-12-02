package com.nazmiev.radik.vkclient.core.db.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.nazmiev.radik.vkclient.core.db.models.TaskHistory

@Dao
interface TaskHistoryDao {

    @Query("select distinct current_d from task_history where task_type = :taskType")
    suspend fun getTaskHistoryDates(taskType: Int): List<String>

    @Query("DELETE from task_history where task_type = :taskType and current_d = :currentDate")
    suspend fun deleteTaskHistory(taskType: Int, currentDate: String)

    @Insert
    suspend fun addTaskHistory(taskHistory: TaskHistory)
}