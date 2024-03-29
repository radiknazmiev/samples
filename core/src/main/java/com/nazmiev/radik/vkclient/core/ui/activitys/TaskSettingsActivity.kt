package com.nazmiev.radik.vkclient.core.ui.activitys

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Surface
import androidx.compose.ui.unit.dp
import com.nazmiev.radik.vkclient.core.ui.fragments.acceptfriendstasksettings.AcceptFriendsTaskSettings
import com.nazmiev.radik.vkclient.core.ui.fragments.posting.PostingTaskSettings
import com.nazmiev.radik.vkclient.core.ui.theme.AppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TaskSettingsActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val taskType = intent.extras?.getString(FIELD_TASK_TYPE)

        setContent {
            AppTheme {
                Surface {
                    when (taskType) {
                        TASK_ACCEPT_FRIENDS -> {
                            val finish =
                                { withResult: Boolean, taskId: Int, isRepeatTask: Boolean, repeatPeriod: Long, isSendMessage: Boolean ->
                                    if (withResult) {
                                        setResult(
                                            0,
                                            Intent().putExtra(FIELD_TASK_TYPE, taskType).putExtra(FIELD_TASK_ID, taskId)
                                                .putExtra(
                                                    FIELD_TASK_IS_REPEAT, isRepeatTask
                                                ).putExtra(FIELD_TASK_REPEAT_PERIOD, repeatPeriod)
                                                .putExtra(FIELD_TASK_SEND_ACCOMPANYING_MESSAGES, isSendMessage)
                                        )
                                    }
                                    finish()
                                }

                            AcceptFriendsTaskSettings(finish = finish)
                        }
                        TASK_POSTING        -> {
                            val finish =
                                { withResult: Boolean, taskId: Int, isRepeatTask: Boolean, repeatPeriod: Long, settingId: Int ->
                                    if (withResult) {
                                        setResult(
                                            0,
                                            Intent().putExtra(FIELD_TASK_TYPE, taskType).putExtra(FIELD_TASK_ID, taskId)
                                                .putExtra(
                                                    FIELD_TASK_IS_REPEAT, isRepeatTask
                                                ).putExtra(FIELD_TASK_REPEAT_PERIOD, repeatPeriod)
                                                .putExtra(FIELD_TASK_GROUP_SEARCH_SETTING_ID, settingId)
                                        )
                                    }
                                    finish()
                                }

                            PostingTaskSettings(finish = finish)
                        }
                    }
                }
            }
        }
    }


    companion object {
        const val FIELD_TASK_TYPE = "task_type"
        const val FIELD_TASK_IS_REPEAT = "task_is_repeat"
        const val FIELD_TASK_ID = "task_id"
        const val FIELD_TASK_REPEAT_PERIOD = "task_repeat_period"
        const val FIELD_TASK_SEND_ACCOMPANYING_MESSAGES = "task_send_accompanying_messages"
        const val FIELD_TASK_GROUP_SEARCH_SETTING_ID = "task_group_search_setting_id"

        const val TASK_ACCEPT_FRIENDS = "task_accept_friends"
        const val TASK_POSTING = "task_posting"
    }
}