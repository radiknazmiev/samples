package com.nazmiev.radik.vkclient.core.db.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "global_settings")
data class GlobalSetting(
    @ColumnInfo(name = "is_start_task_manager")
    val isStartTaskManager: Int?,
    @ColumnInfo(name = "notif_sound_activated", defaultValue = "true")
    val isNotificationSoundActivated: String,
    @ColumnInfo(name = "notif_vibration_activated", defaultValue = "true")
    val isNotificationVibrationActivated: String,
)
{
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "_id")
    var id: Int = 0
}
