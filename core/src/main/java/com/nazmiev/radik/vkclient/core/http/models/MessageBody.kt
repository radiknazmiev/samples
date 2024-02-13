package com.nazmiev.radik.vkclient.core.http.models

import com.google.gson.annotations.SerializedName
import com.nazmiev.radik.vkclient.core.utils.Constants

data class MessageBody(
    @SerializedName("user_id")
    val userId: Int,
    @SerializedName("random_id")
    val randomId: Int,
    @SerializedName("message")
    val message: String,
    @SerializedName("attachment")
    val attachment: String,
    @SerializedName("captcha_key")
    val captchaKey: String?,
    @SerializedName("captcha_sid")
    val captchaSid: String?,
    @SerializedName("access_token")
    val accessToken: String,
    @SerializedName("v")
    val apiVersion: String = Constants.VK_API_VERSION
)
