package com.nazmiev.radik.vkclient.core.db.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "sent_invitations")
data class SentInvitations(
    @ColumnInfo(name = "user_id")
    val userId: Int?,
    @ColumnInfo(name = "invite_sent_date")
    val inviteSentDate: String?,
)
{
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "_id")
    var id: Int = 0
}
