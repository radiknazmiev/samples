package com.nazmiev.radik.vkclient.core.repositories

import com.nazmiev.radik.vkclient.core.db.AppDatabase
import com.nazmiev.radik.vkclient.core.db.models.SelectedAccountForTask
import com.nazmiev.radik.vkclient.core.db.models.Task
import com.nazmiev.radik.vkclient.core.db.models.TaskHistory
import com.nazmiev.radik.vkclient.core.db.models.User
import javax.inject.Inject

class TaskRepositoryImpl @Inject constructor(private val db: AppDatabase): TaskRepository {

    override suspend fun getTaskHistoryDates(taskType: Int): List<String> {
        return db.taskHistoryDao().getTaskHistoryDates(taskType)
    }

    override suspend fun deleteTaskHistory(taskType: Int, currentDate: String) {
        db.taskHistoryDao().deleteTaskHistory(taskType, currentDate)
    }

    override suspend fun addTaskHistory(taskHistory: TaskHistory) {
        db.taskHistoryDao().addTaskHistory(taskHistory)
    }

    override suspend fun addNewTask(taskType: Int, taskName: String, currentDateTime: String): Long {
        val task = Task(taskName, taskType, currentDateTime, 0)

        return db.taskDao().addNewTask(task)
    }

    override suspend fun addUsersToTask(taskId: Int, users: List<Int>) {
        val selectedAccountForTask = users.map {
            SelectedAccountForTask(taskId, it)
        }
        db.selectedAccountsForTaskDao().clean()
        db.selectedAccountsForTaskDao().addAccountForTask(selectedAccountForTask)
    }

    override suspend fun setTaskStatus(taskId: Int, status: Int) {
        db.taskDao().setTaskStatus(taskId, status)
    }

    override suspend fun deleteTask(taskId: Int) {
        db.taskDao().deleteTask(taskId)
    }

    override suspend fun getTaskUsers(taskId: Int): List<User> {
        return db.selectedAccountsForTaskDao().getTaskAccounts(taskId)
    }
}