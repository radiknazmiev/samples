package com.nazmiev.radik.vkclient.core.db.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.nazmiev.radik.vkclient.core.db.models.Task

@Dao
interface TaskDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addNewTask(task: Task): Long

    @Query("UPDATE tasks set status = :status where _id = :taskId")
    suspend fun setTaskStatus(taskId: Int, status: Int)

    @Query("DELETE FROM tasks where _id = :taskId")
    suspend fun deleteTask(taskId: Int)
}