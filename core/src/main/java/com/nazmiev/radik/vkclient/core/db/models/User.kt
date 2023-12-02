package com.nazmiev.radik.vkclient.core.db.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class User (
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "_id")
    val id: Int,
    @ColumnInfo(name = "acc_tocken")
    val accessToken: String?,
    @ColumnInfo(name = "last_search")
    val lastSearch: String?,
    @ColumnInfo(name = "is_def")
    val isDefault: Int?,
    @ColumnInfo(name = "login")
    val login: String?,
    @ColumnInfo(name = "requests_count")
    val requestsCount: Int?,
    @ColumnInfo(name = "incomplite_search")
    val incompleteSearch: String?,
    @ColumnInfo(name = "skip_processing")
    val skipProcessing: Int?,
    @ColumnInfo(name = "acc_title")
    val accountTitle: String?,
    @ColumnInfo(name = "is_partner_acc")
    val isPartnerAccount: Int?,
    @ColumnInfo(name = "sort_index")
    val sortIndex: Int?,
    @ColumnInfo(name = "last_liking", defaultValue = "01.01.1900 00:00:00")
    val lastLiking: String?,
    @ColumnInfo(name = "last_message", defaultValue = "01.01.1900 00:00:00")
    val lastMessage: String?,
    @ColumnInfo(name = "account_login")
    val accountLogin: String?,
    @ColumnInfo(name = "account_password")
    val accountPassword: String?,
    @ColumnInfo(name = "updated_at")
    val updatedAt: String?,
)