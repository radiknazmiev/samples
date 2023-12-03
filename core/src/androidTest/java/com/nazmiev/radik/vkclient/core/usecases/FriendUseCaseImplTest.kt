package com.nazmiev.radik.vkclient.core.usecases

import com.nazmiev.radik.vkclient.core.di.HttpModule
import com.nazmiev.radik.vkclient.core.http.HttpService
import com.nazmiev.radik.vkclient.core.repositories.FriendsRepositoryImpl
import androidx.test.platform.app.InstrumentationRegistry
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*

import org.junit.Before
import org.junit.Test

class FriendUseCaseImplTest {

    private lateinit var friendUseCaseImpl: FriendUseCaseImpl

    @Before
    fun setUp() {
        val friendsRepositoryImpl = FriendsRepositoryImpl()
        val httpModule = HttpModule()
        val baseUrl = httpModule.providesBaseUrl()
        val okHttpClient = httpModule.provideOkhttpClient()
        val retrofit = httpModule.provideRetrofit(baseUrl, okHttpClient)
        val mainService = httpModule.provideMainService(retrofit)
        friendUseCaseImpl = FriendUseCaseImpl(friendsRepositoryImpl, mainService)
    }

    @Test
    fun initRepository() {
    }

    @Test
    fun getOutRequests() {
        runBlocking {
            val response = friendUseCaseImpl.addFriend(
                accessToken = "vk1.a.oXGEUNgGpjRJGvQTdVJEezMKL8L43eZMnsp3j-AM5UEpHGVw1eO8r6l4n_RjAPvmDgImIjkUc4XjLbDD-hRzD9Omv5HMb7KgKSpDgAwpermMRoQ43NUGj-BKtlxaw1I1CG3PI451RTlReOzjnVK8MljcVSrjtgYpBeuMZ__PGnTTGew2ec0ueSYlb5kHA6L5",
                0
            )

            assert(response.response != null)
        }
    }

    @Test
    fun getIncomingRequests() {
    }

    @Test
    fun addFriend() {
    }
}