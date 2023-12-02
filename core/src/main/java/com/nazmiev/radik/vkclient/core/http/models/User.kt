package com.nazmiev.radik.vkclient.core.http.models

import com.google.gson.annotations.SerializedName

data class User(
    val id                     : Int?     = null,
    val bdate                  : String?  = null,
    val photo200               : String?  = null,
    val hasPhoto               : Int?     = null,
    val canWritePrivateMessage : Int?     = null,
    val canSendFriendRequest   : Int?     = null,
    val canBeInvitedGroup      : Boolean? = null,
    val blacklisted            : Int?     = null,
    val blacklistedByMe        : Int?     = null,
    val sex                    : Int?     = null,
    val photo50                : String?  = null,
    val photo100               : String?  = null,
    val firstName              : String?  = null,
    val lastName               : String?  = null,
    val canAccessClosed        : Boolean? = null,
    val isClosed               : Boolean? = null,

    val error: Error?,

    var isChecked: Boolean = false
)
