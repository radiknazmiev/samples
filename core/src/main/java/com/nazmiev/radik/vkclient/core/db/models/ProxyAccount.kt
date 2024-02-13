package com.nazmiev.radik.vkclient.core.db.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "proxy_account")
data class ProxyAccount(
    @ColumnInfo(name = "account")
    val account: Int,
    @ColumnInfo(name = "proxy")
    val proxy: Int,
)
{
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "_id")
    var id: Int = 0
}
