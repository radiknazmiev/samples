package com.nazmiev.radik.vkclient.core.http.models

import com.google.gson.annotations.SerializedName

data class FriendAdd(
    @SerializedName("response") val response: Int?,
    @SerializedName("error") val error: Error?
)
