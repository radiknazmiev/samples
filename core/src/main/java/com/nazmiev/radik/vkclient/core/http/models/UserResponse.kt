package com.nazmiev.radik.vkclient.core.http.models

import com.google.gson.annotations.SerializedName

data class UserResponse(
    @SerializedName("response") val response: List<Response>?,
    @SerializedName("error") val error: Error?
)
{
    data class Response (
        @SerializedName("id"                        ) var id                     : Int?     = null,
        @SerializedName("bdate"                     ) var bdate                  : String?  = null,
        @SerializedName("photo_200"                 ) var photo200               : String?  = null,
        @SerializedName("has_photo"                 ) var hasPhoto               : Int?     = null,
        @SerializedName("can_write_private_message" ) var canWritePrivateMessage : Int?     = null,
        @SerializedName("can_send_friend_request"   ) var canSendFriendRequest   : Int?     = null,
        @SerializedName("can_be_invited_group"      ) var canBeInvitedGroup      : Boolean? = null,
        @SerializedName("blacklisted"               ) var blacklisted            : Int?     = null,
        @SerializedName("blacklisted_by_me"         ) var blacklistedByMe        : Int?     = null,
        @SerializedName("sex"                       ) var sex                    : Int?     = null,
        @SerializedName("photo_50"                  ) var photo50                : String?  = null,
        @SerializedName("photo_100"                 ) var photo100               : String?  = null,
        @SerializedName("first_name"                ) var firstName              : String?  = null,
        @SerializedName("last_name"                 ) var lastName               : String?  = null,
        @SerializedName("can_access_closed"         ) var canAccessClosed        : Boolean? = null,
        @SerializedName("is_closed"                 ) var isClosed               : Boolean? = null

    )
}
