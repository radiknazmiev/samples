package com.nazmiev.radik.vkclient.core.usecases

import com.nazmiev.radik.vkclient.core.db.models.TaskHistory
import com.nazmiev.radik.vkclient.core.db.models.User
import com.nazmiev.radik.vkclient.core.repositories.TaskRepository
import javax.inject.Inject

class TaskUseCaseImpl @Inject constructor(private val taskRepository: TaskRepository) :
    TaskUseCase {

    override suspend fun addNewTask(
        taskType: Int,
        taskName: String,
        currentDateTime: String
    ): Long {
        return taskRepository.addNewTask(taskType, taskName, currentDateTime)
    }

    override suspend fun addUsersToTask(taskId: Int, users: List<Int>) {
        return taskRepository.addUsersToTask(taskId, users)
    }

    override suspend fun setTaskStatus(taskId: Int, status: Int) {
        return taskRepository.setTaskStatus(taskId, status)
    }

    override suspend fun deleteTask(taskId: Int) {
        return taskRepository.deleteTask(taskId)
    }

    override suspend fun getTaskUsers(taskId: Int): List<User> {
        return taskRepository.getTaskUsers(taskId)
    }

    override suspend fun addTaskHistory(taskHistory: TaskHistory) {
        return taskRepository.addTaskHistory(taskHistory)
    }

    override suspend fun deleteTaskHistory(taskType: Int, currentDate: String) {
        return taskRepository.deleteTaskHistory(taskType, currentDate)
    }

    override suspend fun getTaskHistoryDates(taskType: Int): List<String> {
        return taskRepository.getTaskHistoryDates(taskType)
    }
}