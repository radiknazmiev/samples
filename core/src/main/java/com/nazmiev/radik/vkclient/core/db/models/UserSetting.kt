package com.nazmiev.radik.vkclient.core.db.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_settings")
data class UserSetting(
    @ColumnInfo(name = "access_token")
    val accessToken: String?,
    @ColumnInfo(name = "country")
    val country: Int?,
    @ColumnInfo(name = "city")
    val city: Int?,
    @ColumnInfo(name = "city_name")
    val cityName: String?,
    @ColumnInfo(name = "age_from")
    val ageFrom: Int?,
    @ColumnInfo(name = "age_to")
    val ageTo: Int?,
    @ColumnInfo(name = "sex")
    val sex: Int?,
    @ColumnInfo(name = "is_online")
    val isOnline: Int?,
    @ColumnInfo(name = "month_birth")
    val monthBirth: Int?,
    @ColumnInfo(name = "year_birth")
    val yearBirth: Int?,
    @ColumnInfo(name = "acc_max_requests")
    val accMaxRequests: Int?,
    @ColumnInfo(name = "group_id")
    val groupId: Int?,
    @ColumnInfo(name = "transmittal_message")
    val transmittalMessage: String?,
    @ColumnInfo(name = "is_send_transmittal_message")
    val isSendTransmittalMessage: Int?,
    @ColumnInfo(name = "birth_day")
    val birthDay: Int?,
    @ColumnInfo(name = "acc_max_messages")
    val accountMaxMessages: Int?,
    @ColumnInfo(name = "acc_max_messages_at_time")
    val accountMaxMessagesAtTime: Int?,
    @ColumnInfo(name = "acc_max_requests_at_time")
    val accountMaxRequestsAtTime: Int?,
    @ColumnInfo(name = "user_id")
    val userId: Int?,
)
{
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "_id")
    var id: Int = 0
}
