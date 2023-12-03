package com.nazmiev.radik.vkclient.core.http.models

import com.google.gson.annotations.SerializedName

data class CaptchaRequestBody(
    @SerializedName("key") val key: String,
    @SerializedName("method") val method: String = "base64",
    @SerializedName("numeric") val numeric: String = "4",
    @SerializedName("language") val language: String = "0",
    @SerializedName("soft_id") val softId: String,
    @SerializedName("body") val body: String,
)