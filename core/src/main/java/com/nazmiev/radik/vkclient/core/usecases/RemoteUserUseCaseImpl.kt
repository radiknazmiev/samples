package com.nazmiev.radik.vkclient.core.usecases

import com.nazmiev.radik.vkclient.core.http.models.User
import com.nazmiev.radik.vkclient.core.repositories.UserRepository
import javax.inject.Inject

class RemoteUserUseCaseImpl @Inject constructor(private val userRepository: UserRepository) :
    RemoteUserUseCase {

    override suspend fun getUsersInfo(
        accessToken: String,
        userIds: List<String>,
        fields: List<String>
    ): List<User> {
        return userRepository.getUsersInfo(accessToken, userIds, fields)
    }
}