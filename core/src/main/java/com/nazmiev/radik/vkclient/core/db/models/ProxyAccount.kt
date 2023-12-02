package com.nazmiev.radik.vkclient.core.db.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "proxy_accaunt")
data class ProxyAccount(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "_id")
    val id: Int,
    @ColumnInfo(name = "accaunt")
    val account: Int,
    @ColumnInfo(name = "proxy")
    val proxy: Int,
)
