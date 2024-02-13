package com.nazmiev.radik.vkclient.core.db.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "proxy_list")
data class ProxyList(
    @ColumnInfo(name = "hostname")
    val hostname: String,
    @ColumnInfo(name = "port")
    val port: Int,
    @ColumnInfo(name = "proxy_login")
    val proxyLogin: String,
    @ColumnInfo(name = "proxy_password")
    val proxyPassword: String,
    @ColumnInfo(name = "is_def")
    val isDefault: Int?,
    @ColumnInfo(name = "account")
    val account: Int?,
)
{
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "_id")
    var id: Int = 0
}
