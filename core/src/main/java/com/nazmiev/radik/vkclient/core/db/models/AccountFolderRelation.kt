package com.nazmiev.radik.vkclient.core.db.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "account_folder_relation")
data class AccountFolderRelation(
    @ColumnInfo(name = "user_id")
    val userId: Int,
    @ColumnInfo(name = "pid")
    val pid: Int,
)
{
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "_id")
    var id: Int = 0
}
