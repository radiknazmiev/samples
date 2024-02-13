package com.nazmiev.radik.vkclient.core.db.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "planning_post_comments")
data class PlanningPostComment(
    @ColumnInfo(name = "planning_post_id")
    val planningPostId: Int?,
    @ColumnInfo(name = "comment_id")
    val commentId: Int?,
    @ColumnInfo(name = "comment_name")
    val commentName: String?,
)
{
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "_id")
    var id: Int = 0
}
