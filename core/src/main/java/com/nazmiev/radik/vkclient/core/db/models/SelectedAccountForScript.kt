package com.nazmiev.radik.vkclient.core.db.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "selected_accounts_for_script")
data class SelectedAccountForScript(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "_id")
    val id: Int,
    @ColumnInfo(name = "script_id")
    val scriptId: Int?,
    @ColumnInfo(name = "accaunt")
    val account: Int?,
)
