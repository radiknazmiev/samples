package com.nazmiev.radik.vkclient.core.repositories

import com.nazmiev.radik.vkclient.core.Constants
import com.nazmiev.radik.vkclient.core.db.AppDatabase
import com.nazmiev.radik.vkclient.core.http.HttpService
import com.nazmiev.radik.vkclient.core.http.models.User
import javax.inject.Inject
import javax.inject.Named

class UserRepositoryImpl @Inject constructor(
    @Named("VKAPI") private val httpService: HttpService,
    private val db: AppDatabase
) : UserRepository {

    override suspend fun getUsersInfo(
        accessToken: String,
        userIds: List<String>,
        fields: List<String>
    ): List<User> {
        val queryMap = object : HashMap<String, String>() {
            init {
                put("access_token", accessToken)
                put("user_ids", userIds.joinToString(","))
                put("fields", fields.joinToString(","))
                put("v", Constants.VK_API_VERSION)
            }
        }

        val response = httpService.getUsersInfo(queryMap)
        val users = mutableListOf<User>()
        response.response?.forEach {
            users.add(
                User(
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
            )
        }
        return users
    }

    override suspend fun getAllUsers(): List<com.nazmiev.radik.vkclient.core.db.models.User> {
        return db.userDao().getAllUsers()
    }
}