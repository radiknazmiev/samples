package com.nazmiev.radik.vkclient.core.repositories

import com.nazmiev.radik.vkclient.core.db.models.TaskHistory
import com.nazmiev.radik.vkclient.core.db.models.User

interface TaskRepository {

    suspend fun addNewTask(taskType: Int, taskName: String, currentDateTime: String): Long

    suspend fun addUsersToTask(taskId: Int, users: List<Int>)

    suspend fun setTaskStatus(taskId: Int, status: Int)

    suspend fun deleteTask(taskId: Int)

    suspend fun getTaskUsers(taskId: Int): List<User>

    suspend fun addTaskHistory(taskHistory: TaskHistory)

    suspend fun deleteTaskHistory(taskType: Int, currentDate: String)

    suspend fun getTaskHistoryDates(taskType: Int): List<String>
}