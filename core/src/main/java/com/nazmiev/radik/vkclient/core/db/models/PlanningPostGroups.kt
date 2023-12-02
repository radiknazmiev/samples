package com.nazmiev.radik.vkclient.core.db.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "planning_post_groups")
data class PlanningPostGroups(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "_id")
    val id: Int,
    @ColumnInfo(name = "planning_post_id")
    val planningPostId: Int?,
    @ColumnInfo(name = "group_id")
    val groupId: Int?,
    @ColumnInfo(name = "group_name")
    val groupName: Int?,
)
