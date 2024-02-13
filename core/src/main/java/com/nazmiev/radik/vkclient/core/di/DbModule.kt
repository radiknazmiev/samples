package com.nazmiev.radik.vkclient.core.di

import android.content.Context
import androidx.room.Room
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import androidx.sqlite.db.SupportSQLiteOpenHelper
import com.nazmiev.radik.vkclient.core.db.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DbModule {

    private val MIGRATION_53_54 = object : Migration(53, 54) {
        override fun migrate(database: SupportSQLiteDatabase) {
            database.execSQL("CREATE TABLE if not exists cookies_tmp (`user_id` INTEGER NOT NULL, `user_agent` INTEGER, `cookie_name` TEXT NOT NULL, `cookie_value` TEXT NOT NULL)")
            database.execSQL("INSERT INTO cookies_tmp (`user_id`, `user_agent`, `cookie_name`, `cookie_value`) SELECT `user_id`, `user_agent`, `cookie_name`, `cookie_value` FROM cookies")
            database.execSQL("DROP TABLE cookies")
            database.execSQL("CREATE TABLE if not exists cookies (`_id` INTEGER NOT NULL, `user_id` INTEGER NOT NULL, `user_agent` INTEGER, `cookie_name` TEXT NOT NULL, `cookie_value` TEXT NOT NULL, PRIMARY KEY(`_id`))")
            database.execSQL("INSERT INTO cookies (`user_id`, `user_agent`, `cookie_name`, `cookie_value`) SELECT `user_id`, `user_agent`, `cookie_name`, `cookie_value` FROM cookies_tmp")
            database.execSQL("DROP TABLE cookies_tmp")

            database.execSQL("CREATE TABLE if not exists foaf_tmp (`user_id` INTEGER NOT NULL, `create_date` TEXT NOT NULL, `modified_date` TEXT NOT NULL, `last_logged_in` INTEGER NOT NULL)")
            database.execSQL("INSERT INTO foaf_tmp (`user_id`, `create_date`, `modified_date`, `last_logged_in`) SELECT `user_id`, `create_date`, `modified_date`, `last_logged_in` FROM foaf")
            database.execSQL("DROP TABLE foaf")
            database.execSQL("CREATE TABLE if not exists foaf (`_id` INTEGER NOT NULL, `user_id` INTEGER NOT NULL, `create_date` TEXT NOT NULL, `modified_date` TEXT NOT NULL, `last_logged_in` INTEGER NOT NULL, PRIMARY KEY(`_id`))")
            database.execSQL("INSERT INTO foaf (`user_id`, `create_date`, `modified_date`, `last_logged_in`) SELECT `user_id`, `create_date`, `modified_date`, `last_logged_in` FROM foaf_tmp")
            database.execSQL("DROP TABLE foaf_tmp")

            database.execSQL("CREATE TABLE if not exists welcome_message_tmp (`user_id` INTEGER, `message_id` INTEGER)")
            database.execSQL("INSERT INTO welcome_message_tmp (`user_id`, `message_id`) SELECT `user_id`, `message_id` FROM welcom_message")
            database.execSQL("DROP TABLE welcom_message")
            database.execSQL("CREATE TABLE if not exists welcome_message (`_id` INTEGER NOT NULL, `user_id` INTEGER, `message_id` INTEGER, PRIMARY KEY(`_id`))")
            database.execSQL("INSERT INTO welcome_message (`user_id`, `message_id`) SELECT `user_id`, `message_id` FROM welcome_message_tmp")
            database.execSQL("DROP TABLE welcome_message_tmp")

            database.execSQL("CREATE TABLE if not exists vk_groups_tmp (`user_id` INTEGER, `group_id` INTEGER)")
            database.execSQL("INSERT INTO vk_groups_tmp (`user_id`, `group_id`) SELECT `user_id`, `group_id` FROM vk_groups")
            database.execSQL("DROP TABLE vk_groups")
            database.execSQL("CREATE TABLE if not exists vk_groups (`_id` INTEGER NOT NULL, `user_id` INTEGER, `group_id` INTEGER, PRIMARY KEY(`_id`))")
            database.execSQL("INSERT INTO vk_groups (`user_id`, `group_id`) SELECT `user_id`, `group_id` FROM vk_groups_tmp")
            database.execSQL("DROP TABLE vk_groups_tmp")

            database.execSQL("CREATE TABLE if not exists user_settings_tmp (`access_token` TEXT, `country` INTEGER, `city` INTEGER, `city_name` TEXT, `age_from` INTEGER, `age_to` INTEGER, `sex` INTEGER, `is_online` INTEGER, `month_birth` INTEGER, `year_birth` INTEGER, `acc_max_requests` INTEGER, `group_id` INTEGER, `transmittal_message` TEXT, `is_send_transmittal_message` INTEGER, `birth_day` INTEGER, `acc_max_messages` INTEGER, `acc_max_messages_at_time` INTEGER, `acc_max_requests_at_time` INTEGER, `user_id` INTEGER)")
            database.execSQL("INSERT INTO user_settings_tmp (`access_token`, `country`, `city`, `city_name`, `age_from`, `age_to`, `sex`, `is_online`, `month_birth`, `year_birth`, `acc_max_requests`, `group_id`, `transmittal_message`, `is_send_transmittal_message`, `birth_day`, `acc_max_messages`, `acc_max_messages_at_time`, `acc_max_requests_at_time`, `user_id`) SELECT `acc_tocken`, `country`, `city`, `city_name`, `age_from`, `age_to`, `sex`, `is_online`, `month_birth`, `year_birth`, `acc_max_requests`, `group_id`, `transmittal_message`, `is_send_transmittal_message`, `birth_day`, `acc_max_messages`, `acc_max_messages_at_time`, `acc_max_requests_at_time`, `user_id` FROM user_settings")
            database.execSQL("DROP TABLE user_settings")
            database.execSQL("CREATE TABLE if not exists user_settings (`_id` INTEGER NOT NULL, `access_token` TEXT, `country` INTEGER, `city` INTEGER, `city_name` TEXT, `age_from` INTEGER, `age_to` INTEGER, `sex` INTEGER, `is_online` INTEGER, `month_birth` INTEGER, `year_birth` INTEGER, `acc_max_requests` INTEGER, `group_id` INTEGER, `transmittal_message` TEXT, `is_send_transmittal_message` INTEGER, `birth_day` INTEGER, `acc_max_messages` INTEGER, `acc_max_messages_at_time` INTEGER, `acc_max_requests_at_time` INTEGER, `user_id` INTEGER, PRIMARY KEY(`_id`))")
            database.execSQL("INSERT INTO user_settings (`access_token`, `country`, `city`, `city_name`, `age_from`, `age_to`, `sex`, `is_online`, `month_birth`, `year_birth`, `acc_max_requests`, `group_id`, `transmittal_message`, `is_send_transmittal_message`, `birth_day`, `acc_max_messages`, `acc_max_messages_at_time`, `acc_max_requests_at_time`, `user_id`) SELECT `access_token`, `country`, `city`, `city_name`, `age_from`, `age_to`, `sex`, `is_online`, `month_birth`, `year_birth`, `acc_max_requests`, `group_id`, `transmittal_message`, `is_send_transmittal_message`, `birth_day`, `acc_max_messages`, `acc_max_messages_at_time`, `acc_max_requests_at_time`, `user_id` FROM user_settings_tmp")
            database.execSQL("DROP TABLE user_settings_tmp")

            database.execSQL("CREATE TABLE if not exists tasks_tmp (`name` TEXT, `type` INTEGER, `scheduled_start_time` TEXT, `status` INTEGER)")
            database.execSQL("INSERT INTO tasks_tmp (`name`, `type`, `scheduled_start_time`, `status`) SELECT `name`, `type`, `scheduled_start_time`, `status` FROM tasks")
            database.execSQL("DROP TABLE tasks")
            database.execSQL("CREATE TABLE if not exists tasks (`_id` INTEGER NOT NULL, `name` TEXT, `type` INTEGER, `scheduled_start_time` TEXT, `status` INTEGER, PRIMARY KEY(`_id`))")
            database.execSQL("CREATE UNIQUE INDEX `index_tasks_type` ON `tasks` (`type`);")
            database.execSQL("INSERT INTO tasks (`name`, `type`, `scheduled_start_time`, `status`) SELECT `name`, `type`, `scheduled_start_time`, `status` FROM tasks_tmp")
            database.execSQL("DROP TABLE tasks_tmp")

            database.execSQL("CREATE TABLE if not exists task_progress_tmp (`type` INTEGER, `account_number` INTEGER, `current_requests_count` INTEGER, `last_date` TEXT, `is_full` INTEGER, `is_payed` INTEGER, `thread_number` INTEGER, `account` INTEGER)")
            database.execSQL("INSERT INTO task_progress_tmp (`type`, `account_number`, `current_requests_count`, `last_date`, `is_full`, `is_payed`, `thread_number`, `account`) SELECT `type`, `account_number`, `current_requests_count`, `last_date`, `is_full`, `is_payed`, `thread_number`, `accaunt` FROM task_progress")
            database.execSQL("DROP TABLE task_progress")
            database.execSQL("CREATE TABLE if not exists task_progress (`_id` INTEGER NOT NULL, `type` INTEGER, `account_number` INTEGER, `current_requests_count` INTEGER, `last_date` TEXT, `is_full` INTEGER, `is_payed` INTEGER, `thread_number` INTEGER, `account` INTEGER, PRIMARY KEY(`_id`))")
            database.execSQL("INSERT INTO task_progress (`type`, `account_number`, `current_requests_count`, `last_date`, `is_full`, `is_payed`, `thread_number`, `account`) SELECT `type`, `account_number`, `current_requests_count`, `last_date`, `is_full`, `is_payed`, `thread_number`, `account` FROM task_progress_tmp")
            database.execSQL("DROP TABLE task_progress_tmp")

            database.execSQL("CREATE TABLE if not exists sent_invitations_tmp (`user_id` INTEGER, `invite_sent_date` TEXT)")
            database.execSQL("INSERT INTO sent_invitations_tmp (`user_id`, `invite_sent_date`) SELECT `user_id`, `invite_sent_date` FROM sent_invitations")
            database.execSQL("DROP TABLE sent_invitations")
            database.execSQL("CREATE TABLE if not exists sent_invitations (`_id` INTEGER NOT NULL, `user_id` INTEGER, `invite_sent_date` TEXT, PRIMARY KEY(`_id`))")
            database.execSQL("INSERT INTO sent_invitations (`user_id`, `invite_sent_date`) SELECT `user_id`, `invite_sent_date` FROM sent_invitations_tmp")
            database.execSQL("DROP TABLE sent_invitations_tmp")

            database.execSQL("CREATE TABLE if not exists selected_accounts_for_script_tmp (`script_id` INTEGER, `account` INTEGER)")
            database.execSQL("INSERT INTO selected_accounts_for_script_tmp (`script_id`, `account`) SELECT `script_id`, `accaunt` FROM selected_accounts_for_script")
            database.execSQL("DROP TABLE selected_accounts_for_script")
            database.execSQL("CREATE TABLE if not exists selected_accounts_for_script (`_id` INTEGER NOT NULL, `script_id` INTEGER, `account` INTEGER, PRIMARY KEY(`_id`))")
            database.execSQL("INSERT INTO selected_accounts_for_script (`script_id`, `account`) SELECT `script_id`, `account` FROM selected_accounts_for_script_tmp")
            database.execSQL("DROP TABLE selected_accounts_for_script_tmp")

            database.execSQL("CREATE TABLE if not exists selected_account_selected_chats_tmp (`chat_id` INTEGER, `account` INTEGER)")
            database.execSQL("INSERT INTO selected_account_selected_chats_tmp (`chat_id`, `account`) SELECT `chat_id`, `accaunt` FROM selected_account_selected_chats")
            database.execSQL("DROP TABLE selected_account_selected_chats")
            database.execSQL("CREATE TABLE if not exists selected_account_selected_chats (`_id` INTEGER NOT NULL, `chat_id` INTEGER, `account` INTEGER, PRIMARY KEY(`_id`))")
            database.execSQL("INSERT INTO selected_account_selected_chats (`chat_id`, `account`) SELECT `chat_id`, `account` FROM selected_account_selected_chats_tmp")
            database.execSQL("DROP TABLE selected_account_selected_chats_tmp")

            database.execSQL("CREATE TABLE if not exists search_settings_tmp (`account` INTEGER, `search_source` INTEGER, `invite_to_group` INTEGER, `group_link` INTEGER, `sent_message` INTEGER, `attraction_source` INTEGER, `sort` INTEGER)")
            database.execSQL("INSERT INTO search_settings_tmp (`account`, `search_source`, `invite_to_group`, `group_link`, `sent_message`, `attraction_source`, `sort`) SELECT `accaunt`, `search_source`, `invite_to_group`, `group_link`, `sent_message`, `attraction_source`, `sort` FROM search_settings")
            database.execSQL("DROP TABLE search_settings")
            database.execSQL("CREATE TABLE if not exists search_settings (`_id` INTEGER NOT NULL, `account` INTEGER, `search_source` INTEGER, `invite_to_group` INTEGER, `group_link` INTEGER, `sent_message` INTEGER, `attraction_source` INTEGER, `sort` INTEGER, PRIMARY KEY(`_id`))")
            database.execSQL("INSERT INTO search_settings (`account`, `search_source`, `invite_to_group`, `group_link`, `sent_message`, `attraction_source`, `sort`) SELECT `account`, `search_source`, `invite_to_group`, `group_link`, `sent_message`, `attraction_source`, `sort` FROM search_settings_tmp")
            database.execSQL("DROP TABLE search_settings_tmp")

            database.execSQL("CREATE TABLE if not exists scripts_tmp (`name` TEXT, `type` INTEGER, `status` INTEGER)")
            database.execSQL("INSERT INTO scripts_tmp (`name`, `type`, `status`) SELECT `name`, `type`, `status` FROM scripts")
            database.execSQL("DROP TABLE scripts")
            database.execSQL("CREATE TABLE if not exists scripts (`_id` INTEGER NOT NULL, `name` TEXT, `type` INTEGER, `status` INTEGER, PRIMARY KEY(`_id`))")
            database.execSQL("INSERT INTO scripts (`name`, `type`, `status`) SELECT `name`, `type`, `status` FROM scripts_tmp")
            database.execSQL("DROP TABLE scripts_tmp")

            database.execSQL("CREATE TABLE if not exists response_message_tmp (`incoming_phrase` TEXT, `message_id` INTEGER, `previous_message` INTEGER, `processed_text` TEXT, `match_percentage` INTEGER)")
            database.execSQL("INSERT INTO response_message_tmp (`incoming_phrase`, `message_id`, `previous_message`, `processed_text`, `match_percentage`) SELECT `incoming_phrase`, `message_id`, `previous_message`, `processed_text`, `match_percentage` FROM response_message")
            database.execSQL("DROP TABLE response_message")
            database.execSQL("CREATE TABLE if not exists response_message (`_id` INTEGER NOT NULL, `incoming_phrase` TEXT, `message_id` INTEGER, `previous_message` INTEGER, `processed_text` TEXT, `match_percentage` INTEGER, PRIMARY KEY(`_id`))")
            database.execSQL("INSERT INTO response_message (`incoming_phrase`, `message_id`, `previous_message`, `processed_text`, `match_percentage`) SELECT `incoming_phrase`, `message_id`, `previous_message`, `processed_text`, `match_percentage` FROM response_message_tmp")
            database.execSQL("DROP TABLE response_message_tmp")

            database.execSQL("CREATE TABLE if not exists processed_users_tmp (`user_id` INTEGER, `operation_type` INTEGER)")
            database.execSQL("INSERT INTO processed_users_tmp (`user_id`, `operation_type`) SELECT `user_id`, `operation_type` FROM processed_users")
            database.execSQL("DROP TABLE processed_users")
            database.execSQL("CREATE TABLE if not exists processed_users (`_id` INTEGER NOT NULL, `user_id` INTEGER, `operation_type` INTEGER, PRIMARY KEY(`_id`))")
            database.execSQL("INSERT INTO processed_users (`user_id`, `operation_type`) SELECT `user_id`, `operation_type` FROM processed_users_tmp")
            database.execSQL("DROP TABLE processed_users_tmp")

            database.execSQL("CREATE TABLE if not exists processed_task_tmp (`task_id` INTEGER, `task_type` INTEGER)")
            database.execSQL("INSERT INTO processed_task_tmp (`task_id`, `task_type`) SELECT `task_id`, `task_type` FROM processed_task")
            database.execSQL("DROP TABLE processed_task")
            database.execSQL("CREATE TABLE if not exists processed_task (`_id` INTEGER NOT NULL, `task_id` INTEGER, `task_type` INTEGER, PRIMARY KEY(`_id`))")
            database.execSQL("INSERT INTO processed_task (`task_id`, `task_type`) SELECT `task_id`, `task_type` FROM processed_task_tmp")
            database.execSQL("DROP TABLE processed_task_tmp")

            database.execSQL("CREATE TABLE if not exists private_message_tmp (`user_id` INTEGER, `message_id` INTEGER)")
            database.execSQL("INSERT INTO private_message_tmp (`user_id`, `message_id`) SELECT `user_id`, `message_id` FROM private_message")
            database.execSQL("DROP TABLE private_message")
            database.execSQL("CREATE TABLE if not exists private_message (`_id` INTEGER NOT NULL, `user_id` INTEGER, `message_id` INTEGER, PRIMARY KEY(`_id`))")
            database.execSQL("INSERT INTO private_message (`user_id`, `message_id`) SELECT `user_id`, `message_id` FROM private_message_tmp")
            database.execSQL("DROP TABLE private_message_tmp")

            database.execSQL("CREATE TABLE if not exists posting_account_wall_settings_tmp (`script_id` INTEGER, `post_id` INTEGER)")
            database.execSQL("INSERT INTO posting_account_wall_settings_tmp (`script_id`, `post_id`) SELECT `script_id`, `post_id` FROM posting_account_wall_settings")
            database.execSQL("DROP TABLE posting_account_wall_settings")
            database.execSQL("CREATE TABLE if not exists posting_account_wall_settings (`_id` INTEGER NOT NULL, `script_id` INTEGER, `post_id` INTEGER, PRIMARY KEY(`_id`))")
            database.execSQL("INSERT INTO posting_account_wall_settings (`script_id`, `post_id`) SELECT `script_id`, `post_id` FROM posting_account_wall_settings_tmp")
            database.execSQL("DROP TABLE posting_account_wall_settings_tmp")

            database.execSQL("CREATE TABLE if not exists planning_post_settings_tmp (`post_id` INTEGER, `user_id` INTEGER, `user_name` TEXT, `planning_date_time` TEXT, `repost` INTEGER, `set_like` INTEGER, `planning_post_message` TEXT, `set_comment` INTEGER, `comment_pause` INTEGER, `post_on_telegram` INTEGER, `telegram_chats` TEXT)")
            database.execSQL("INSERT INTO planning_post_settings_tmp (`post_id`, `user_id`, `user_name`, `planning_date_time`, `repost`, `set_like`, `planning_post_message`, `set_comment`, `comment_pause`, `post_on_telegram`, `telegram_chats`) SELECT `post_id`, `user_id`, `user_name`, `planning_date_time`, `repost`, `set_like`, `planning_post_message`, `set_comment`, `comment_pause`, `post_on_telegram`, `telegram_chats` FROM planning_post_settings")
            database.execSQL("DROP TABLE planning_post_settings")
            database.execSQL("CREATE TABLE if not exists planning_post_settings (`_id` INTEGER NOT NULL, `post_id` INTEGER, `user_id` INTEGER, `user_name` TEXT, `planning_date_time` TEXT, `repost` INTEGER, `set_like` INTEGER, `planning_post_message` TEXT, `set_comment` INTEGER, `comment_pause` INTEGER, `post_on_telegram` INTEGER, `telegram_chats` TEXT, PRIMARY KEY(`_id`))")
            database.execSQL("INSERT INTO planning_post_settings (`post_id`, `user_id`, `user_name`, `planning_date_time`, `repost`, `set_like`, `planning_post_message`, `set_comment`, `comment_pause`, `post_on_telegram`, `telegram_chats`) SELECT `post_id`, `user_id`, `user_name`, `planning_date_time`, `repost`, `set_like`, `planning_post_message`, `set_comment`, `comment_pause`, `post_on_telegram`, `telegram_chats` FROM planning_post_settings_tmp")
            database.execSQL("DROP TABLE planning_post_settings_tmp")

            database.execSQL("CREATE TABLE if not exists planning_post_groups_tmp (`planning_post_id` INTEGER, `group_id` INTEGER, `group_name` INTEGER)")
            database.execSQL("INSERT INTO planning_post_groups_tmp (`planning_post_id`, `group_id`, `group_name`) SELECT `planning_post_id`, `group_id`, `group_name` FROM planning_post_groups")
            database.execSQL("DROP TABLE planning_post_groups")
            database.execSQL("CREATE TABLE if not exists planning_post_groups (`_id` INTEGER NOT NULL, `planning_post_id` INTEGER, `group_id` INTEGER, `group_name` INTEGER, PRIMARY KEY(`_id`))")
            database.execSQL("INSERT INTO planning_post_groups (`planning_post_id`, `group_id`, `group_name`) SELECT `planning_post_id`, `group_id`, `group_name` FROM planning_post_groups_tmp")
            database.execSQL("DROP TABLE planning_post_groups_tmp")

            database.execSQL("CREATE TABLE if not exists planning_post_comments_tmp (`planning_post_id` INTEGER, `comment_id` INTEGER, `comment_name` TEXT)")
            database.execSQL("INSERT INTO planning_post_comments_tmp (`planning_post_id`, `comment_id`, `comment_name`) SELECT `planning_post_id`, `comment_id`, `comment_name` FROM planning_post_comments")
            database.execSQL("DROP TABLE planning_post_comments")
            database.execSQL("CREATE TABLE if not exists planning_post_comments (`_id` INTEGER NOT NULL, `planning_post_id` INTEGER, `comment_id` INTEGER, `comment_name` TEXT, PRIMARY KEY(`_id`))")
            database.execSQL("INSERT INTO planning_post_comments (`planning_post_id`, `comment_id`, `comment_name`) SELECT `planning_post_id`, `comment_id`, `comment_name` FROM planning_post_comments_tmp")
            database.execSQL("DROP TABLE planning_post_comments_tmp")

            database.execSQL("CREATE TABLE if not exists messages_attachments_tmp (`message_id` INTEGER, `attach_id` INTEGER, `attach_owner_id` INTEGER, `attach_src` TEXT, `access_key` TEXT, `attach_type` TEXT, `cover_url` TEXT, `file_path` TEXT)")
            database.execSQL("INSERT INTO messages_attachments_tmp (`message_id`, `attach_id`, `attach_owner_id`, `attach_src`, `access_key`, `attach_type`, `cover_url`, `file_path`) SELECT `message_id`, `attach_id`, `attach_owner_id`, `attach_src`, `access_key`, `attach_type`, `cover_url`, `file_path` FROM messages_attachments")
            database.execSQL("DROP TABLE messages_attachments")
            database.execSQL("CREATE TABLE if not exists messages_attachments (`_id` INTEGER NOT NULL, `message_id` INTEGER, `attach_id` INTEGER, `attach_owner_id` INTEGER, `attach_src` TEXT, `access_key` TEXT, `attach_type` TEXT, `cover_url` TEXT, `file_path` TEXT, PRIMARY KEY(`_id`))")
            database.execSQL("INSERT INTO messages_attachments (`message_id`, `attach_id`, `attach_owner_id`, `attach_src`, `access_key`, `attach_type`, `cover_url`, `file_path`) SELECT `message_id`, `attach_id`, `attach_owner_id`, `attach_src`, `access_key`, `attach_type`, `cover_url`, `file_path` FROM messages_attachments_tmp")
            database.execSQL("DROP TABLE messages_attachments_tmp")

            database.execSQL("CREATE TABLE if not exists messages_tmp (`user_id` INTEGER, `message_text` TEXT, `title` TEXT, `type` INTEGER)")
            database.execSQL("INSERT INTO messages_tmp (`user_id`, `message_text`, `title`, `type`) SELECT `user_id`, `message_text`, `title`, `type` FROM messages")
            database.execSQL("DROP TABLE messages")
            database.execSQL("CREATE TABLE if not exists messages (`_id` INTEGER NOT NULL, `user_id` INTEGER, `message_text` TEXT, `title` TEXT, `type` INTEGER, PRIMARY KEY(`_id`))")
            database.execSQL("INSERT INTO messages (`user_id`, `message_text`, `title`, `type`) SELECT `user_id`, `message_text`, `title`, `type` FROM messages_tmp")
            database.execSQL("DROP TABLE messages_tmp")

            database.execSQL("CREATE TABLE if not exists link_to_users_from_file_tmp (`user_id` INTEGER, `vk_user_id` INTEGER)")
            database.execSQL("INSERT INTO link_to_users_from_file_tmp (`user_id`, `vk_user_id`) SELECT `user_id`, `vk_user_id` FROM link_to_users")
            database.execSQL("DROP TABLE link_to_users_from_file")
            database.execSQL("CREATE TABLE if not exists link_to_users_from_file (`_id` INTEGER NOT NULL, `user_id` INTEGER, `vk_user_id` INTEGER, PRIMARY KEY(`_id`))")
            database.execSQL("INSERT INTO link_to_users_from_file (`user_id`, `vk_user_id`) SELECT `user_id`, `vk_user_id` FROM link_to_users_from_file_tmp")
            database.execSQL("DROP TABLE link_to_users_from_file_tmp")

            database.execSQL("CREATE TABLE if not exists link_to_users_tmp (`user_id` INTEGER, `vk_user_id` INTEGER)")
            database.execSQL("INSERT INTO link_to_users_tmp (`user_id`, `vk_user_id`) SELECT `user_id`, `vk_user_id` FROM link_to_users")
            database.execSQL("DROP TABLE link_to_users")
            database.execSQL("CREATE TABLE if not exists link_to_users (`_id` INTEGER NOT NULL, `user_id` INTEGER, `vk_user_id` INTEGER, PRIMARY KEY(`_id`))")
            database.execSQL("INSERT INTO link_to_users (`user_id`, `vk_user_id`) SELECT `user_id`, `vk_user_id` FROM link_to_users_tmp")
            database.execSQL("DROP TABLE link_to_users_tmp")

            database.execSQL("CREATE TABLE if not exists groups_for_processing_tmp (`pid` INTEGER, `group_id` INTEGER, `is_member` INTEGER)")
            database.execSQL("INSERT INTO groups_for_processing_tmp (`pid`, `group_id`, `is_member`) SELECT `pid`, `group_id`, `is_member` FROM groups_for_processing")
            database.execSQL("DROP TABLE groups_for_processing")
            database.execSQL("CREATE TABLE if not exists groups_for_processing (`_id` INTEGER NOT NULL, `pid` INTEGER NOT NULL, `group_id` INTEGER NOT NULL, `is_member` INTEGER NOT NULL, PRIMARY KEY(`_id`))")
            database.execSQL("INSERT INTO groups_for_processing (`pid`, `group_id`, `is_member`) SELECT `pid`, `group_id`, `is_member` FROM groups_for_processing_tmp")
            database.execSQL("DROP TABLE groups_for_processing_tmp")

            database.execSQL("CREATE TABLE if not exists groups_for_posting_tmp (`pid` INTEGER, `group_id` INTEGER)")
            database.execSQL("INSERT INTO groups_for_posting_tmp (`pid`, `group_id`) SELECT `pid`, `group_id` FROM groups_for_posting")
            database.execSQL("DROP TABLE groups_for_posting")
            database.execSQL("CREATE TABLE if not exists groups_for_posting (`_id` INTEGER NOT NULL, `pid` INTEGER NOT NULL, `group_id` INTEGER NOT NULL, PRIMARY KEY(`_id`))")
            database.execSQL("INSERT INTO groups_for_posting (`pid`, `group_id`) SELECT `pid`, `group_id` FROM groups_for_posting_tmp")
            database.execSQL("DROP TABLE groups_for_posting_tmp")

            database.execSQL("CREATE TABLE if not exists group_search_setting_tmp (`user_id` INTEGER, `search_source` INTEGER, `country` INTEGER, `city` INTEGER, `phrase` TEXT, `sort` INTEGER, `group_count_from` INTEGER, `group_count_to` INTEGER, `message_id` INTEGER, `group_type` INTEGER, `auto_liking_post` INTEGER, `filed_post` INTEGER, `pre_join_group` INTEGER, `setting_title` TEXT)")
            database.execSQL("INSERT INTO group_search_setting_tmp (user_id, search_source, country, city, phrase, sort, group_count_from, group_count_to, message_id, group_type, auto_liking_post, filed_post, pre_join_group, setting_title) SELECT user_id, search_source, country, city, phrase, sort, group_count_from, group_count_to, message_id, group_type, auto_liking_post, filed_post, pre_join_group, setting_title FROM group_search_setting")
            database.execSQL("DROP TABLE group_search_setting")
            database.execSQL("CREATE TABLE if not exists group_search_setting (`_id` INTEGER NOT NULL, `user_id` INTEGER, `search_source` INTEGER, `country` INTEGER, `city` INTEGER, `phrase` TEXT, `sort` INTEGER, `group_count_from` INTEGER, `group_count_to` INTEGER, `message_id` INTEGER, `group_type` INTEGER, `auto_liking_post` INTEGER, `filed_post` INTEGER, `pre_join_group` INTEGER, `setting_title` TEXT, PRIMARY KEY(`_id`))")
            database.execSQL("INSERT INTO group_search_setting (user_id, search_source, country, city, phrase, sort, group_count_from, group_count_to, message_id, group_type, auto_liking_post, filed_post, pre_join_group, setting_title) SELECT user_id, search_source, country, city, phrase, sort, group_count_from, group_count_to, message_id, group_type, auto_liking_post, filed_post, pre_join_group, setting_title FROM group_search_setting_tmp")
            database.execSQL("DROP TABLE group_search_setting_tmp")

            database.execSQL("CREATE TABLE if not exists group_chat_message_tmp (user_id INTEGER, message_id INTEGER)")
            database.execSQL("INSERT INTO group_chat_message_tmp (user_id, message_id) SELECT user_id, message_id FROM group_chat_message")
            database.execSQL("DROP TABLE group_chat_message")
            database.execSQL("CREATE TABLE if not exists group_chat_message (`_id` INTEGER NOT NULL, `user_id` INTEGER NOT NULL, `message_id` INTEGER NOT NULL, PRIMARY KEY(`_id`))")
            database.execSQL("INSERT INTO group_chat_message (user_id, message_id) SELECT user_id, message_id FROM group_chat_message_tmp")
            database.execSQL("DROP TABLE group_chat_message_tmp")

            database.execSQL("CREATE TABLE if not exists account_folder_relation_tmp (user_id INTEGER, pid INTEGER)")
            database.execSQL("INSERT INTO account_folder_relation_tmp (user_id, pid) SELECT user_id, pid FROM account_folder_relation")
            database.execSQL("DROP TABLE account_folder_relation")
            database.execSQL("CREATE TABLE if not exists account_folder_relation (`_id` INTEGER NOT NULL, user_id INTEGER NOT NULL, pid INTEGER NOT NULL, PRIMARY KEY(`_id`))")
            database.execSQL("INSERT INTO account_folder_relation (user_id, pid) SELECT user_id, pid FROM account_folder_relation_tmp")
            database.execSQL("DROP TABLE account_folder_relation_tmp")

            database.execSQL("CREATE TABLE if not exists account_folders_tmp (name TEXT)")
            database.execSQL("INSERT INTO account_folders_tmp (name) SELECT name FROM account_folders")
            database.execSQL("DROP TABLE account_folders")
            database.execSQL("CREATE TABLE if not exists account_folders (`_id` INTEGER NOT NULL, name TEXT NOT NULL, PRIMARY KEY(`_id`))")
            database.execSQL("INSERT INTO account_folders (name) SELECT name FROM account_folders_tmp")
            database.execSQL("DROP TABLE account_folders_tmp")

            database.execSQL("CREATE TABLE if not exists global_settings_tmp (is_start_task_manager INTEGER, notif_sound_activated TEXT, notif_vibration_activated TEXT)")
            database.execSQL("INSERT INTO global_settings_tmp (is_start_task_manager, notif_sound_activated, notif_vibration_activated) SELECT is_start_task_manager, notif_sound_activated, notif_vibration_activated FROM global_settings")
            database.execSQL("DROP TABLE global_settings")
            database.execSQL("CREATE TABLE if not exists global_settings (`_id` INTEGER NOT NULL, is_start_task_manager INTEGER, notif_sound_activated TEXT NOT NULL DEFAULT true, notif_vibration_activated TEXT NOT NULL DEFAULT true, PRIMARY KEY(`_id`))")
            database.execSQL("INSERT INTO global_settings (is_start_task_manager, notif_sound_activated, notif_vibration_activated) SELECT is_start_task_manager, notif_sound_activated, notif_vibration_activated FROM global_settings_tmp")
            database.execSQL("DROP TABLE global_settings_tmp")

            database.execSQL("CREATE TABLE if not exists proxy_list_tmp (hostname TEXT, port INTEGER, proxy_login TEXT, proxy_password TEXT, is_def INTEGER, account INTEGER)")
            database.execSQL("INSERT INTO proxy_list_tmp (hostname, port, proxy_login, proxy_password, is_def, account) SELECT hostname, port, proxy_login, proxy_password, is_def, accaunt FROM proxy_list")
            database.execSQL("DROP TABLE proxy_list")
            database.execSQL("CREATE TABLE if not exists proxy_list (`_id` INTEGER NOT NULL, hostname TEXT NOT NULL, port INTEGER NOT NULL, proxy_login TEXT NOT NULL, proxy_password TEXT NOT NULL, is_def INTEGER, account INTEGER, PRIMARY KEY(`_id`))")
            database.execSQL("INSERT INTO proxy_list (hostname, port, proxy_login, proxy_password, is_def, account) SELECT hostname, port, proxy_login, proxy_password, is_def, account FROM proxy_list_tmp")
            database.execSQL("DROP TABLE proxy_list_tmp")

            database.execSQL("CREATE TABLE if not exists proxy_account_tmp (account INTEGER, proxy INTEGER)")
            database.execSQL("INSERT INTO proxy_account_tmp (account, proxy) SELECT accaunt, proxy FROM proxy_accaunt")
            database.execSQL("DROP TABLE proxy_accaunt")
            database.execSQL("CREATE TABLE if not exists proxy_account (`_id` INTEGER NOT NULL, account INTEGER NOT NULL, proxy INTEGER NOT NULL, PRIMARY KEY(`_id`))")
            database.execSQL("INSERT INTO proxy_account (account, proxy) SELECT account, proxy FROM proxy_account_tmp")
            database.execSQL("DROP TABLE proxy_account_tmp")

            database.execSQL("CREATE TABLE if not exists user_agents_tmp (`user_id` INTEGER, `user_agent` TEXT NOT NULL);")
            database.execSQL("INSERT INTO user_agents_tmp (user_id, user_agent) " +
                    "SELECT user_id, user_agent FROM user_agents"
            )
            database.execSQL("DROP TABLE user_agents")
            database.execSQL("CREATE TABLE if not exists user_agents (`_id` INTEGER NOT NULL, `user_agent` TEXT NOT NULL, `user_id` INTEGER, PRIMARY KEY(`_id`))")
            database.execSQL("INSERT INTO user_agents (user_id, user_agent) SELECT user_id, user_agent FROM user_agents_tmp")
            database.execSQL("DROP TABLE user_agents_tmp")

            database.execSQL("CREATE TABLE if not exists selected_accounts_for_task_tmp (`task_id` INTEGER NOT NULL, `account` INTEGER NOT NULL);")
            database.execSQL("INSERT INTO selected_accounts_for_task_tmp (task_id, account) " +
                    "SELECT task_id, accaunt FROM selected_accounts_for_task"
            )
            database.execSQL("DROP TABLE selected_accounts_for_task")
            database.execSQL("CREATE TABLE if not exists selected_accounts_for_task (`_id` INTEGER NOT NULL, `task_id` INTEGER NOT NULL, `account` INTEGER NOT NULL, PRIMARY KEY(`_id`))")
            database.execSQL("INSERT INTO selected_accounts_for_task (task_id, account) SELECT task_id, account FROM selected_accounts_for_task_tmp")
            database.execSQL("DROP TABLE selected_accounts_for_task_tmp")

            database.execSQL("CREATE TABLE if not exists users_tmp (`access_token` TEXT, `last_search` TEXT, `is_def` INTEGER, `login` TEXT, `requests_count` INTEGER, `incomplete_search` TEXT, `skip_processing` INTEGER, `acc_title` TEXT, `is_partner_acc` INTEGER, `sort_index` INTEGER, `last_liking` TEXT DEFAULT '01.01.1900 00:00:00', `last_message` TEXT DEFAULT '01.01.1900 00:00:00', `account_login` TEXT, `account_password` TEXT, `updated_at` TEXT);")
            database.execSQL("INSERT INTO users_tmp (access_token, last_search, is_def, login, requests_count, incomplete_search, skip_processing, acc_title, is_partner_acc, sort_index, last_liking, last_message, account_login, account_password, updated_at) " +
                    "SELECT acc_tocken, last_search, is_def, login, requests_count, incomplite_search, skip_processing, acc_title, is_partner_acc, sort_index, last_liking, last_message, account_login, account_password, updated_at FROM users"
            )
            database.execSQL("DROP TABLE users")
            database.execSQL("CREATE TABLE if not exists users (`_id` INTEGER NOT NULL, `access_token` TEXT, `last_search` TEXT, `is_def` INTEGER, `login` TEXT, `requests_count` INTEGER, `incomplete_search` TEXT, `skip_processing` INTEGER, `acc_title` TEXT, `is_partner_acc` INTEGER, `sort_index` INTEGER, `last_liking` TEXT DEFAULT '01.01.1900 00:00:00', `last_message` TEXT DEFAULT '01.01.1900 00:00:00', `account_login` TEXT, `account_password` TEXT, `updated_at` TEXT, PRIMARY KEY(`_id`))")
            database.execSQL("INSERT INTO users (access_token, last_search, is_def, login, requests_count, incomplete_search, skip_processing, acc_title, is_partner_acc, sort_index, last_liking, last_message, account_login, account_password, updated_at) " +
                    "SELECT access_token, last_search, is_def, login, requests_count, incomplete_search, skip_processing, acc_title, is_partner_acc, sort_index, last_liking, last_message, account_login, account_password, updated_at FROM users_tmp"
            )
            database.execSQL("DROP TABLE users_tmp")

            database.execSQL("CREATE TABLE if not exists task_history_tmp (task_type integer,history_text text,history_time text,current_d text,user_id integer,thread_number integer);")
            database.execSQL("INSERT INTO task_history_tmp(task_type, history_text, history_time, current_d, user_id, thread_number)\n" +
                    "SELECT th.task_type, th.history_text, th.history_time, th.current_d, th.user_id, th.thread_number FROM task_history th")
            database.execSQL("drop table task_history")
            database.execSQL("CREATE TABLE if not exists task_history (`id` INTEGER NOT NULL,task_type integer NOT NULL,history_text text NOT NULL,history_time text NOT NULL,current_d text NOT NULL,user_id integer NOT NULL,thread_number integer NOT NULL, PRIMARY KEY(`id`))")
            database.execSQL("INSERT INTO task_history(task_type, history_text, history_time, current_d, user_id, thread_number)\n" +
                    "SELECT th.task_type, th.history_text, th.history_time, th.current_d, th.user_id, th.thread_number FROM task_history_tmp th")
            database.execSQL("drop table task_history_tmp")
        }
    }

    private val MIGRATION_54_55 = object : Migration(54, 55) {
        override fun migrate(database: SupportSQLiteDatabase) {
            database.execSQL("CREATE TABLE if not exists groups_black_list (`group_id` INTEGER NOT NULL)")
            database.execSQL("CREATE TABLE if not exists groups_black_list_tmp (`group_id` INTEGER NOT NULL);")
            database.execSQL("INSERT INTO groups_black_list_tmp (group_id) " +
                    "SELECT group_id FROM groups_black_list"
            )
            database.execSQL("DROP TABLE groups_black_list")
            database.execSQL("CREATE TABLE if not exists groups_black_list (`_id` INTEGER NOT NULL, `group_id` INTEGER NOT NULL, PRIMARY KEY(`_id`))")
            database.execSQL("INSERT INTO groups_black_list (group_id) SELECT group_id FROM groups_black_list_tmp")
            database.execSQL("DROP TABLE groups_black_list_tmp")
        }

    }

    private val MIGRATION_55_56 = object : Migration(55, 56) {
        override fun migrate(database: SupportSQLiteDatabase) {
            database.execSQL("CREATE TABLE if not exists account_statistic (`static_date` TEXT NOT NULL, account_id INTEGER NOT NULL, fr_request_count INTEGER NOT NULL, messages_count INTEGER NOT NULL, likes_count INTEGER NOT NULL, posts_count INTEGER NOT NULL, unfollow_count INTEGER NOT NULL)")
            database.execSQL("CREATE TABLE if not exists account_statistic_tmp (`static_date` TEXT NOT NULL, account_id INTEGER NOT NULL, fr_request_count INTEGER NOT NULL, messages_count INTEGER NOT NULL, likes_count INTEGER NOT NULL, posts_count INTEGER NOT NULL, unfollow_count INTEGER NOT NULL);")
            database.execSQL("INSERT INTO account_statistic_tmp (`static_date`, account_id, fr_request_count, messages_count, likes_count, posts_count, unfollow_count) " +
                    "SELECT `static_date`, account_id, fr_request_count, messages_count, likes_count, posts_count, unfollow_count FROM account_statistic"
            )
            database.execSQL("DROP TABLE account_statistic")
            database.execSQL("CREATE TABLE if not exists account_statistic (`_id` INTEGER NOT NULL, `static_date` TEXT NOT NULL, account_id INTEGER NOT NULL, fr_request_count INTEGER NOT NULL DEFAULT 0, messages_count INTEGER NOT NULL DEFAULT 0, likes_count INTEGER NOT NULL DEFAULT 0, posts_count INTEGER NOT NULL DEFAULT 0, unfollow_count INTEGER NOT NULL DEFAULT 0, PRIMARY KEY(`_id`))")
            database.execSQL("INSERT INTO account_statistic (`static_date`, account_id, fr_request_count, messages_count, likes_count, posts_count, unfollow_count) SELECT `static_date`, account_id, fr_request_count, messages_count, likes_count, posts_count, unfollow_count FROM account_statistic_tmp")
            database.execSQL("DROP TABLE account_statistic_tmp")
        }

    }

    @Provides
    @Singleton
    fun provideDb(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java, "usersDB"
        )
//            .fallbackToDestructiveMigration()
            .addMigrations(MIGRATION_53_54)
            .addMigrations(MIGRATION_54_55)
            .addMigrations(MIGRATION_55_56)
            .build()
    }

    @Provides
    @Singleton
    fun provideOpenHelper(db: AppDatabase): SupportSQLiteOpenHelper {
        return db.openHelper
    }
}