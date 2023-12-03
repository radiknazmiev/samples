package com.nazmiev.radik.vkclient.core.repositories

import android.content.SharedPreferences
import com.nazmiev.radik.vkclient.core.Constants.CAPTCHA_ANTI_CAPTCHA_KEY
import com.nazmiev.radik.vkclient.core.Constants.CAPTCHA_BOT_CAPTCHA_KEY
import com.nazmiev.radik.vkclient.core.Constants.CAPTCHA_CPTCH_KEY
import com.nazmiev.radik.vkclient.core.Constants.CAPTCHA_DEFAULT_ANTIGATE_SERVICE
import com.nazmiev.radik.vkclient.core.Constants.CAPTCHA_RUCAPTCHA_KEY
import com.nazmiev.radik.vkclient.core.http.CaptchaHttpService
import com.nazmiev.radik.vkclient.core.http.models.CaptchaRequestBody
import okhttp3.MediaType
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import javax.inject.Inject
import javax.inject.Named

class CaptchaRepositoryImpl @Inject constructor(
    private val preferences: SharedPreferences,
    @Named("anticaptcha") private val anticaptchaService: CaptchaHttpService,
    @Named("rucaptcha") private val rucaptchaService: CaptchaHttpService,
    @Named("cptch") private val cptchService: CaptchaHttpService,
): CaptchaRepository {

    override suspend fun setCaptcha(body: String): String {

        return when (preferences.getString(CAPTCHA_DEFAULT_ANTIGATE_SERVICE, "")) {
            "rucapcha" -> {
                val key = preferences.getString(CAPTCHA_RUCAPTCHA_KEY, "")
                val captchaRequestBody = CaptchaRequestBody(key = key!!, body = body, softId = "2094")
                rucaptchaService.setCaptcha(
                    body = captchaRequestBody.body.toRequestBody("text/plain".toMediaTypeOrNull()),
                    key = captchaRequestBody.key.toRequestBody("text/plain".toMediaTypeOrNull()),
                    language = captchaRequestBody.language.toRequestBody("text/plain".toMediaTypeOrNull()),
                    method = captchaRequestBody.method.toRequestBody("text/plain".toMediaTypeOrNull()),
                    numeric = captchaRequestBody.numeric.toRequestBody("text/plain".toMediaTypeOrNull()),
                    softId = captchaRequestBody.softId.toRequestBody("text/plain".toMediaTypeOrNull())
                )
            }
            "cptch" -> {
                val key = preferences.getString(CAPTCHA_CPTCH_KEY, "")
                val captchaRequestBody = CaptchaRequestBody(key = key!!, body = body, softId = "43")
                cptchService.setCaptcha(
                    body = captchaRequestBody.body.toRequestBody("text/plain".toMediaTypeOrNull()),
                    key = captchaRequestBody.key.toRequestBody("text/plain".toMediaTypeOrNull()),
                    language = captchaRequestBody.language.toRequestBody("text/plain".toMediaTypeOrNull()),
                    method = captchaRequestBody.method.toRequestBody("text/plain".toMediaTypeOrNull()),
                    numeric = captchaRequestBody.numeric.toRequestBody("text/plain".toMediaTypeOrNull()),
                    softId = captchaRequestBody.softId.toRequestBody("text/plain".toMediaTypeOrNull())
                )
            }
            "anti-captcha" -> {
                val key = preferences.getString(CAPTCHA_ANTI_CAPTCHA_KEY, "")
                val captchaRequestBody = CaptchaRequestBody(key = key!!, body = body, softId = "888")
                anticaptchaService.setCaptcha(
                    body = captchaRequestBody.body.toRequestBody("text/plain".toMediaTypeOrNull()),
                    key = captchaRequestBody.key.toRequestBody("text/plain".toMediaTypeOrNull()),
                    language = captchaRequestBody.language.toRequestBody("text/plain".toMediaTypeOrNull()),
                    method = captchaRequestBody.method.toRequestBody("text/plain".toMediaTypeOrNull()),
                    numeric = captchaRequestBody.numeric.toRequestBody("text/plain".toMediaTypeOrNull()),
                    softId = captchaRequestBody.softId.toRequestBody("text/plain".toMediaTypeOrNull())
                )
            }
            "3bot" -> {
                val key = preferences.getString(CAPTCHA_BOT_CAPTCHA_KEY, "")
                val captchaRequestBody = CaptchaRequestBody(key = key!!, body = body, softId = "43")
                cptchService.setCaptcha(
                    body = captchaRequestBody.body.toRequestBody("text/plain".toMediaTypeOrNull()),
                    key = captchaRequestBody.key.toRequestBody("text/plain".toMediaTypeOrNull()),
                    language = captchaRequestBody.language.toRequestBody("text/plain".toMediaTypeOrNull()),
                    method = captchaRequestBody.method.toRequestBody("text/plain".toMediaTypeOrNull()),
                    numeric = captchaRequestBody.numeric.toRequestBody("text/plain".toMediaTypeOrNull()),
                    softId = captchaRequestBody.softId.toRequestBody("text/plain".toMediaTypeOrNull())
                )
            }

            else -> {
                ""
            }
        }
    }

    override suspend fun getResolvedCaptcha(captchaId: String): String {

        val options = mutableMapOf(
            "action" to "get",
            "id" to captchaId,
            "json" to "0"
        )

        return when (preferences.getString(CAPTCHA_DEFAULT_ANTIGATE_SERVICE, "")) {
            "rucapcha" -> {
                val key = preferences.getString(CAPTCHA_RUCAPTCHA_KEY, "")
                options["key"] = key!!
                rucaptchaService.getResolvedCaptcha(options)
            }
            "cptch" -> {
                val key = preferences.getString(CAPTCHA_CPTCH_KEY, "")
                options["key"] = key!!
                cptchService.getResolvedCaptcha(options)
            }
            "anti-captcha" -> {
                val key = preferences.getString(CAPTCHA_ANTI_CAPTCHA_KEY, "")
                options["key"] = key!!
                anticaptchaService.getResolvedCaptcha(options)
            }
            "3bot" -> {
                val key = preferences.getString(CAPTCHA_BOT_CAPTCHA_KEY, "")
                options["key"] = key!!
                cptchService.getResolvedCaptcha(options)
            }

            else -> {
                ""
            }
        }
    }

}