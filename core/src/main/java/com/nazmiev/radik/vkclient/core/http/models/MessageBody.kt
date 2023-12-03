package com.nazmiev.radik.vkclient.core.http.models

import com.google.gson.annotations.SerializedName

data class MessageBody(
    @SerializedName("user_id")
    val userId: Int,
    @SerializedName("random_id")
    val randomId: Int,
    @SerializedName("message")
    val message: String,
    @SerializedName("attachment")
    val attachment: String
)
