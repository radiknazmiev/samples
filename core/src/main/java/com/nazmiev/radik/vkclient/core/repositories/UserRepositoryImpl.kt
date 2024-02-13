package com.nazmiev.radik.vkclient.core.repositories

import com.nazmiev.radik.vkclient.core.db.AppDatabase
import com.nazmiev.radik.vkclient.core.http.HttpService
import com.nazmiev.radik.vkclient.core.http.models.Error
import com.nazmiev.radik.vkclient.core.http.models.User
import com.nazmiev.radik.vkclient.core.http.models.UserResponse
import com.nazmiev.radik.vkclient.core.utils.Constants
import kotlinx.coroutines.delay
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody.Companion.toRequestBody
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

    override suspend fun getProfileInfo(accessTokens: List<String>): List<User> {
        val code = "var profileInfo = API.account.getProfileInfo();\n" +
                "var info = API.account.getInfo({\"fields\": \"country\"});\n" +
                "\n" +
                "if(info.country != null){\n" +
                "\treturn profileInfo;\n" +
                "}\n" +
                "\n" +
                "return {};"

        return accessTokens.map { accessToken ->
            delay(100)

            try {
                val response = httpService.getProfileInfo(
                    code = code.toRequestBody("text/plain".toMediaTypeOrNull()),
                    accessToken = accessToken.toRequestBody("text/plain".toMediaTypeOrNull()),
                    apiVersion = Constants.VK_API_VERSION.toRequestBody("text/plain".toMediaTypeOrNull())
                )

                if (response.response != null) {
                    val user = User(
                        id = response.response!!.id,
                        bdate = response.response!!.bdate,
                        photo200 = response.response!!.photo200,
                        sex = response.response!!.sex,
                        firstName = response.response!!.firstName,
                        lastName = response.response!!.lastName,
                        error = null
                    )
                    user.accessToken = accessToken

                    user
                } else {
                    val user = User(error = response.error)
                    user.accessToken = accessToken

                    user
                }
            } catch (ex: Exception) {
                ex.printStackTrace()

                val user = User(error = Error())
                user.accessToken = accessToken

                user
            }
        }

    }

    override suspend fun checkUser(accessToken: String): UserResponse {
        val queryMap = object : HashMap<String, String>() {
            init {
                put("access_token", accessToken)
                put("v", Constants.VK_API_VERSION)
            }
        }
        return httpService.getUsersInfo(queryMap)
    }
}