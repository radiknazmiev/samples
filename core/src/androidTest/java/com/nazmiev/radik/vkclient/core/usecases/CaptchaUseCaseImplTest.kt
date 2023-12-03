package com.nazmiev.radik.vkclient.core.usecases

import android.R.attr.src
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.drawable.BitmapDrawable
import androidx.test.platform.app.InstrumentationRegistry
import coil.ImageLoader
import coil.request.ImageRequest
import coil.request.SuccessResult
import com.nazmiev.radik.vkclient.core.di.AnticaptchaHttpModule
import com.nazmiev.radik.vkclient.core.di.CptchHttpModule
import com.nazmiev.radik.vkclient.core.di.PreferencesModule
import com.nazmiev.radik.vkclient.core.di.RucaptchaHttpModule
import com.nazmiev.radik.vkclient.core.repositories.CaptchaRepositoryImpl
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import java.net.HttpURLConnection
import java.net.URL


class CaptchaUseCaseImplTest {

    private lateinit var captchaUseCaseImpl: CaptchaUseCaseImpl

    @Before
    fun setUp() {
        //SharedPreferences либо надо созадть для тестовой среды, либо перекинуть с обычной среды
        val preferencesModule = PreferencesModule()

        val rucaptchaHttpModule = RucaptchaHttpModule()
        val rucaptchaBaseUrl = rucaptchaHttpModule.providesBaseUrl()
        val rucaptchaOkHttpClient = rucaptchaHttpModule.provideOkhttpClient()
        val rucaptchaRetrofit =
            rucaptchaHttpModule.provideRetrofit(rucaptchaBaseUrl, rucaptchaOkHttpClient)

        val anticaptchaHttpModule = AnticaptchaHttpModule()
        val anticaptchaBaseUrl = anticaptchaHttpModule.providesBaseUrl()
        val anticaptchaOkHttpClient = anticaptchaHttpModule.provideOkhttpClient()
        val anticaptchaRetrofit =
            anticaptchaHttpModule.provideRetrofit(anticaptchaBaseUrl, anticaptchaOkHttpClient)

        val cptchHttpModule = CptchHttpModule()
        val cptchBaseUrl = cptchHttpModule.providesBaseUrl()
        val cptchOkHttpClient = cptchHttpModule.provideOkhttpClient()
        val cptchRetrofit = cptchHttpModule.provideRetrofit(cptchBaseUrl, cptchOkHttpClient)

        val captchaRepositoryImpl = CaptchaRepositoryImpl(
            preferencesModule.provideSharedPreferences(
                InstrumentationRegistry.getInstrumentation().context
            ),
            anticaptchaHttpModule.provideMainService(anticaptchaRetrofit),
            rucaptchaHttpModule.provideMainService(rucaptchaRetrofit),
            cptchHttpModule.provideMainService(cptchRetrofit)
        )
        captchaUseCaseImpl = CaptchaUseCaseImpl(captchaRepositoryImpl)
    }

    @Test
    fun setCaptcha() {
        runBlocking(Dispatchers.IO) {
            val loader = ImageLoader(InstrumentationRegistry.getInstrumentation().context)
            val request = ImageRequest.Builder(InstrumentationRegistry.getInstrumentation().context)
                .data("http://api.vk.com/captcha.php?sid=239633676097&s=1")
                .allowHardware(false)
                .build()

            val loadResult = (loader.execute(request) as SuccessResult).drawable
            val bitmap = (loadResult as BitmapDrawable).bitmap

            val result = captchaUseCaseImpl.setCaptcha(bitmap)

            assert(result.captchaId != null)
        }
    }

    @Test
    fun getResolvedCaptcha() {
    }
}