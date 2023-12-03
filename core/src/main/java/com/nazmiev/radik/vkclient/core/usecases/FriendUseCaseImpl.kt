package com.nazmiev.radik.vkclient.core.usecases

import com.nazmiev.radik.vkclient.core.Constants
import com.nazmiev.radik.vkclient.core.http.HttpService
import com.nazmiev.radik.vkclient.core.http.models.FriendAdd
import com.nazmiev.radik.vkclient.core.http.models.FriendRequests
import com.nazmiev.radik.vkclient.core.repositories.FriendsRepository
import javax.inject.Inject
import javax.inject.Named

class FriendUseCaseImpl @Inject constructor(
    private val friendsRepository: FriendsRepository,
    @Named("VKAPI") private val httpService: HttpService
) :
    FriendUseCase {

    //если забудут передать httpService
    init {
        friendsRepository.httpService = httpService
    }

    override fun initProxiedRepository(httpService: HttpService) {
        friendsRepository.httpService = httpService
    }

    override suspend fun getOutRequests(
        accessToken: String,
        isNeedViewed: Boolean,
        offset: Int,
        count: Int
    ): FriendRequests {
        val queryMap = object : HashMap<String, String>() {
            init {
                put("access_token", accessToken)
                put("v", Constants.VK_API_VERSION)
                put("out", "1")
                put("need_viewed", if (isNeedViewed) "1" else "0")
            }
        }

        return friendsRepository.getRequests(queryMap)
    }

    override suspend fun getIncomingRequests(
        accessToken: String,
        isNeedViewed: Boolean,
        offset: Int,
        count: Int
    ): FriendRequests {
        val queryMap = object : HashMap<String, String>() {
            init {
                put("access_token", accessToken)
                put("v", Constants.VK_API_VERSION)
                put("out", "0")
                put("need_viewed", if (isNeedViewed) "1" else "0")
            }
        }

        return friendsRepository.getRequests(queryMap)
    }

    override suspend fun addFriend(
        accessToken: String,
        friendId: Int,
        text: String?
    ): FriendAdd {
        val queryMap = object : HashMap<String, String>() {
            init {
                put("access_token", accessToken)
                put("v", Constants.VK_API_VERSION)
                put("user_id", friendId.toString())

                if (text != null) {
                    put("text", text)
                }
            }
        }

        return friendsRepository.addFriend(queryMap)
    }
}