package com.nazmiev.radik.vkclient.core.db.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "scripts")
data class Script(
    @ColumnInfo(name = "name")
    val name: String?,
    @ColumnInfo(name = "type")
    val type: Int?,
    @ColumnInfo(name = "status")
    val status: Int?,
)
{
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "_id")
    var id: Int = 0
}

