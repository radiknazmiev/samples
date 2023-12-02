package com.nazmiev.radik.vkclient.core.usecases

import com.nazmiev.radik.vkclient.core.db.models.User
import com.nazmiev.radik.vkclient.core.repositories.UserRepository
import javax.inject.Inject

class LocalUserUseCaseImpl @Inject constructor(private val userRepository: UserRepository): LocalUserUseCase {

    override suspend fun getAllUsers(): List<User> {
        return userRepository.getAllUsers()
    }
}