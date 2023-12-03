package com.nazmiev.radik.vkclient.core.usecases

import android.graphics.Bitmap
import com.nazmiev.radik.vkclient.core.http.models.CaptchaRequestBody
import com.nazmiev.radik.vkclient.core.local.models.CaptchaResponse

interface CaptchaUseCase {

    suspend fun setCaptcha(bitmap: Bitmap): CaptchaResponse

    suspend fun getResolvedCaptcha(captchaId: String): String
}