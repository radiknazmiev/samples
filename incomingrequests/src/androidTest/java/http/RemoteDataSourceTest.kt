package com.nazmiev.radik.vkclient.incomingrequests.http

import androidx.test.platform.app.InstrumentationRegistry
import com.nazmiev.radik.vkclient.core.di.HttpModule
import com.nazmiev.radik.vkclient.core.repositories.FriendsRepositoryImpl
import kotlinx.coroutines.runBlocking
import org.junit.Before

import org.junit.Test
import java.util.HashMap

class RemoteDataSourceTest {

    private lateinit var friendsRepositoryImpl: FriendsRepositoryImpl

    @Before
    fun setUpRetrofit() {
        val httpModule = HttpModule()
        val baseUrl = httpModule.providesBaseUrl()
//        val okHttpClient = httpModule.provideOkhttpClient(InstrumentationRegistry.getInstrumentation().context)
//        val retrofit = httpModule.provideRetrofit(baseUrl, okHttpClient)
//        val mainService = httpModule.provideMainService(retrofit)
//        friendsRepositoryImpl = httpModule.provideMainRemoteData(mainService)
    }

    @Test
    fun getRequests_correctRequest() {
        runBlocking {
            val queryMap = object : HashMap<String, String>() {
                init {
                    put("access_token", "vk1.a.oXGEUNgGpjRJGvQTdVJEezMKL8L43eZMnsp3j-AM5UEpHGVw1eO8r6l4n_RjAPvmDgImIjkUc4XjLbDD-hRzD9Omv5HMb7KgKSpDgAwpermMRoQ43NUGj-BKtlxaw1I1CG3PI451RTlReOzjnVK8MljcVSrjtgYpBeuMZ__PGnTTGew2ec0ueSYlb5kHA6L5")
                    put("v", "5.154")
                    put("need_viewed", "1")
                }
            }

            val response = friendsRepositoryImpl.getRequests(queryMap)
            assert(response.response != null)
        }

    }

    @Test
    fun getRequests_notCorrectRequest() {
        runBlocking {
            val queryMap = object : HashMap<String, String>() {
                init {
                    put("need_viewed", "1")
                }
            }

            val response = friendsRepositoryImpl.getRequests(queryMap)
            assert(response.error != null)
        }

    }
}