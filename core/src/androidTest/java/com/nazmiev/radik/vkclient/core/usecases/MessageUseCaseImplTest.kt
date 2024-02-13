package com.nazmiev.radik.vkclient.core.usecases

import android.content.SharedPreferences
import android.graphics.drawable.BitmapDrawable
import androidx.room.Room
import androidx.test.platform.app.InstrumentationRegistry
import coil.ImageLoader
import coil.request.ImageRequest
import coil.request.SuccessResult
import com.nazmiev.radik.vkclient.core.db.AppDatabase
import com.nazmiev.radik.vkclient.core.db.models.MessagesAttachments
import com.nazmiev.radik.vkclient.core.di.AnticaptchaHttpModule
import com.nazmiev.radik.vkclient.core.di.CptchHttpModule
import com.nazmiev.radik.vkclient.core.di.HttpModule
import com.nazmiev.radik.vkclient.core.di.PreferencesModule
import com.nazmiev.radik.vkclient.core.di.RucaptchaHttpModule
import com.nazmiev.radik.vkclient.core.http.HttpService
import com.nazmiev.radik.vkclient.core.http.models.MessageResponse
import com.nazmiev.radik.vkclient.core.repositories.CaptchaRepositoryImpl
import com.nazmiev.radik.vkclient.core.repositories.MessageRepositoryImpl
import com.nazmiev.radik.vkclient.core.repositories.MessagesRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import java.util.Calendar

class MessageUseCaseImplTest {

    private val context = InstrumentationRegistry.getInstrumentation().context

    lateinit var messagesRepository: MessagesRepository
    lateinit var httpService: HttpService
    lateinit var captchaUseCase: CaptchaUseCase

    @Before
    fun setUp() {
        val httpModule = HttpModule()
        val baseUrl = httpModule.providesBaseUrl()
        val okHttpClient = httpModule.provideOkhttpClient()
        val retrofit = httpModule.provideRetrofit(baseUrl, okHttpClient)
        httpService = httpModule.provideMainService(retrofit)

        val db = Room.databaseBuilder(
            InstrumentationRegistry.getInstrumentation().context,
            AppDatabase::class.java, "usersDB"
        )
            .fallbackToDestructiveMigration()
            .build()

        messagesRepository = MessageRepositoryImpl(db)
        messagesRepository.httpService = httpService

        val sharedPreferences: SharedPreferences = PreferencesModule().provideSharedPreferences(context)

        val anticaptchaHttpModule = AnticaptchaHttpModule()
        val anticaptchaBaseUrl = anticaptchaHttpModule.providesBaseUrl()
        val anticaptchaHttpClient = anticaptchaHttpModule.provideOkhttpClient()
        val anticaptchaRetrofit  = anticaptchaHttpModule.provideRetrofit(anticaptchaBaseUrl, anticaptchaHttpClient)
        val anticaptchaService = anticaptchaHttpModule.provideMainService(anticaptchaRetrofit)

        val rucaptchaHttpModule = RucaptchaHttpModule()
        val rucaptchaBaseUrl = rucaptchaHttpModule.providesBaseUrl()
        val rucaptchaHttpClient = rucaptchaHttpModule.provideOkhttpClient()
        val rucaptchaRetrofit  = rucaptchaHttpModule.provideRetrofit(rucaptchaBaseUrl, rucaptchaHttpClient)
        val rucaptchaService = rucaptchaHttpModule.provideMainService(rucaptchaRetrofit)

        val cptchHttpModule = CptchHttpModule()
        val cptchBaseUrl = cptchHttpModule.providesBaseUrl()
        val cptchHttpClient = cptchHttpModule.provideOkhttpClient()
        val cptchRetrofit  = cptchHttpModule.provideRetrofit(cptchBaseUrl, cptchHttpClient)
        val cptchService = cptchHttpModule.provideMainService(cptchRetrofit)

        val captchaRepositoryImpl = CaptchaRepositoryImpl(sharedPreferences, anticaptchaService, rucaptchaService, cptchService)

        captchaUseCase = CaptchaUseCaseImpl(captchaRepositoryImpl)
    }

    @Test
    fun initProxiedRepository() {
    }

    @Test
    fun sendMessageTest() {
        val userId = 13619541
        val accessToken = "vk1.a.tl3LZVbvgfFpjnt0Dd18od3M0YAa0eVM6eOjUqe25k9nTt8INIiSWXQx1H03oYbgcOc1swgeKqv7Wg6_jMc8Mbbczn5f6KZU_9mmZeu7BoV7Etz7BANubFMz0GaQX7Umm3qMCNIovZvHE8XsgQWeWSDItGYvKJFWkcVtI8Try0cUf2X2PMe9NxVqeHm7yX2i"
        var i = 1
        runBlocking {
            var result: MessageResponse? = null

            while (i < 20) {
                val randomId = Calendar.getInstance().time.time / 1000
                val message = "Привет!"
                result = sendMessage(
                    userId,
                    randomId.toInt(),
                    message,
                    "",
                    null,
                    null,
                    accessToken
                )

                delay(2000)
                i++

            }

            assert(result?.error == null)
        }
    }

    private suspend fun sendMessage(userId: Int,
                            randomId: Int,
                            message: String,
                            attachment: String,
                            captchaKey: String?,
                            captchaSid: String?,
                            accessToken: String
    ): MessageResponse {
        val result = messagesRepository.sendMessage(
            userId,
            randomId,
            message,
            attachment,
            captchaKey,
            captchaSid,
            accessToken
        )

        if (result.error != null && result.error?.errorCode == 14) {
            val captchaSid = result.error!!.captchaSid
            val captchaImg = result.error!!.captchaImg

            val loader = ImageLoader(context)
            val request = ImageRequest.Builder(context)
                .data(captchaImg)
                .allowHardware(false)
                .build()

            val loadResult = (loader.execute(request) as SuccessResult).drawable
            val bitmap = (loadResult as BitmapDrawable).bitmap

            val captchaResponse = captchaUseCase.setCaptcha(bitmap)

            if (captchaResponse.error == null) {
                delay(5000)
                val captchaResult =
                    captchaUseCase.getResolvedCaptcha(captchaResponse.captchaId!!)

                sendMessage(
                    userId,
                    randomId,
                    message,
                    attachment,
                    captchaKey = captchaResult,
                    captchaSid = captchaSid,
                    accessToken
                )
            }
        }

        return result
    }

    @Test
    fun getWelcomeMessage() {
    }

    @Test
    fun getMessageAttachmentsString() {
        val messageUseCase = MessageUseCaseImpl(
            messagesRepository,
            httpService,
            context,
            captchaUseCase
        )
        val messagesAttachments = listOf(
            MessagesAttachments(
                messageId = 0,
                attachId = 457275483,
                attachOwnerId = -49131654,
                attachSrc = null,
                accessKey = null,
                attachType = "photo",
                coverUrl = null,
                filePath = null
            ),
            MessagesAttachments(
                messageId = 0,
                attachId = 457275483,
                attachOwnerId = -49131654,
                attachSrc = null,
                accessKey = "aasdvvlmmwklsasl",
                attachType = "photo",
                coverUrl = null,
                filePath = null
            )
        )

        val attachmentsString = messageUseCase.getMessageAttachmentsString(messagesAttachments)

        assert(attachmentsString.isNotEmpty())
    }
}