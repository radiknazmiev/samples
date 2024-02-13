package com.nazmiev.radik.vkclient.core.db.dao

import androidx.room.Dao
import androidx.room.Query
import com.nazmiev.radik.vkclient.core.db.models.MessageWithAttachments

@Dao
interface MessageDao {

    @Query("select messages._id, messages.user_id, messages.message_text, messages.title, messages.type from welcome_message " +
            "JOIN messages on messages._id = welcome_message.message_id " +
            "where welcome_message.user_id = :userId order by welcome_message._id")
    suspend fun getWelcomeMessageWithAttachments(userId: Int): List<MessageWithAttachments>

}