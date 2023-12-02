package com.nazmiev.radik.vkclient.core.usecases

import com.nazmiev.radik.vkclient.core.db.models.User

interface LocalUserUseCase {

    suspend fun getAllUsers(): List<User>
}