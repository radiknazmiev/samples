package com.nazmiev.radik.vkclient.core.usecases

import com.nazmiev.radik.vkclient.core.db.models.User
import com.nazmiev.radik.vkclient.core.repositories.UserRepository
import kotlinx.coroutines.delay
import javax.inject.Inject
import com.nazmiev.radik.vkclient.core.http.models.User as NetworkUser

class UserUseCaseImpl @Inject constructor(private val userRepository: UserRepository): UserUseCase {

    override suspend fun getAllUsers(): List<User> {
        return userRepository.getAllUsers()
    }

    override suspend fun getUserInfo(
        accessToken: String,
        userIds: List<String>,
        fields: List<String>
    ): List<com.nazmiev.radik.vkclient.core.http.models.User> {
        return userRepository.getUsersInfo(accessToken, userIds, fields)
    }

    override suspend fun getProfileInfo(accessTokens: List<String>): List<com.nazmiev.radik.vkclient.core.http.models.User> {
        return userRepository.getProfileInfo(accessTokens)
    }

    override suspend fun getFirstNotBlockedUser(accessTokens: List<String>, count: Int): NetworkUser {
        val response = userRepository.checkUser(accessTokens[count])
        return if (response.error != null) {
            delay(100)
            getFirstNotBlockedUser(accessTokens, count + 1)
        } else {
            return response.response?.map {
                val user = NetworkUser(
                    it.id,
                    it.bdate,
                    it.photo200,
                    it.hasPhoto,
                    it.canWritePrivateMessage,
                    it.canSendFriendRequest,
                    it.canBeInvitedGroup,
                    it.blacklisted,
                    it.blacklistedByMe,
                    it.sex,
                    it.photo50,
                    it.photo100,
                    it.firstName,
                    it.lastName,
                    it.canAccessClosed,
                    it.isClosed,
                    response.error
                )

                user.accessToken = accessTokens[count]

                user
            }?.get(0)!!
        }
    }
}