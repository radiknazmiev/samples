package com.nazmiev.radik.vkclient.core.repositories

interface CaptchaRepository {

    suspend fun setCaptcha(body: String): String

    suspend fun getResolvedCaptcha(captchaId: String): String
}