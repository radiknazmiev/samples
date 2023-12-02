package com.nazmiev.radik.vkclient.core.http.models

import com.google.gson.annotations.SerializedName

data class FriendRequests(
    @SerializedName("response") val response: Response?,
    @SerializedName("error") val error: Error?
) {
    data class Response(
        @SerializedName("count"        ) var count       : Int           = 0,
        @SerializedName("items"        ) var items       : ArrayList<Int> = arrayListOf(),
        @SerializedName("count_unread" ) var countUnread : Int           = 0,
        @SerializedName("last_viewed"  ) var lastViewed  : Int           = 0
    )
}
