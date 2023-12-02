package com.nazmiev.radik.vkclient.core.db.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.nazmiev.radik.vkclient.core.db.models.SelectedAccountForTask
import com.nazmiev.radik.vkclient.core.db.models.User

@Dao
interface SelectedAccountsForTaskDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addAccountForTask(accounts: List<SelectedAccountForTask>)

    @Delete
    suspend fun deleteAccount(account: SelectedAccountForTask)

    @Query("DELETE FROM selected_accounts_for_task " +
            "WHERE NOT EXISTS (SELECT 1 FROM tasks task WHERE task._id = selected_accounts_for_task.task_id)")
    suspend fun clean()

    @Query("SELECT u.* " +
            "FROM selected_accounts_for_task saft " +
            "JOIN users u ON saft.accaunt = u.login " +
            "WHERE task_id = :taskId")
    suspend fun getTaskAccounts(taskId: Int): List<User>
}