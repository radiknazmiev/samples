package com.nazmiev.radik.vkclient.core.usecases

import android.graphics.Bitmap
import android.util.Base64
import com.nazmiev.radik.vkclient.core.local.models.CaptchaResponse
import com.nazmiev.radik.vkclient.core.repositories.CaptchaRepository
import java.io.ByteArrayOutputStream
import javax.inject.Inject

class CaptchaUseCaseImpl @Inject constructor(private val captchaRepository: CaptchaRepository) :
    CaptchaUseCase {

    override suspend fun setCaptcha(bitmap: Bitmap): CaptchaResponse {
        val baos = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos)
        val byteArrayImage = baos.toByteArray()
        val encodedImage = Base64.encodeToString(byteArrayImage, Base64.DEFAULT)

        val result = captchaRepository.setCaptcha(encodedImage)

        return if (result.contains("OK|")) {
            CaptchaResponse(captchaId = result.split("|")[1])
        } else {
            CaptchaResponse(error = result)
        }
    }

    override suspend fun getResolvedCaptcha(captchaId: String): String {
        return captchaRepository.getResolvedCaptcha(captchaId)
    }
}