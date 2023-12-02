package com.nazmiev.radik.vkclient.core.db.dao

import androidx.room.Dao
import androidx.room.Query
import com.nazmiev.radik.vkclient.core.db.models.User

@Dao
interface UserDao {

    @Query("select * from users")
    suspend fun getAllUsers(): List<User>
}