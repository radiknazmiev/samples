package com.nazmiev.radik.vkclient.core.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.nazmiev.radik.vkclient.core.db.dao.AccountFolderDao
import com.nazmiev.radik.vkclient.core.db.dao.AccountFolderRelationDao
import com.nazmiev.radik.vkclient.core.db.dao.AccountStatisticDao
import com.nazmiev.radik.vkclient.core.db.dao.CookieDao
import com.nazmiev.radik.vkclient.core.db.dao.FoafDao
import com.nazmiev.radik.vkclient.core.db.dao.GlobalSettingDao
import com.nazmiev.radik.vkclient.core.db.dao.GroupChatMessageDao
import com.nazmiev.radik.vkclient.core.db.dao.GroupSearchSettingDao
import com.nazmiev.radik.vkclient.core.db.dao.GroupsBlackListDao
import com.nazmiev.radik.vkclient.core.db.dao.GroupsForPostingDao
import com.nazmiev.radik.vkclient.core.db.dao.GroupsForProcessingDao
import com.nazmiev.radik.vkclient.core.db.dao.LinkToUsersDao
import com.nazmiev.radik.vkclient.core.db.dao.LinkToUsersFromFileDao
import com.nazmiev.radik.vkclient.core.db.dao.MessageDao
import com.nazmiev.radik.vkclient.core.db.dao.MessagesAttachmentsDao
import com.nazmiev.radik.vkclient.core.db.dao.PlanningPostCommentDao
import com.nazmiev.radik.vkclient.core.db.dao.PlanningPostGroupsDao
import com.nazmiev.radik.vkclient.core.db.dao.PlanningPostSettingDao
import com.nazmiev.radik.vkclient.core.db.dao.PostingAccountWallSettingDao
import com.nazmiev.radik.vkclient.core.db.dao.PrivateMessageDao
import com.nazmiev.radik.vkclient.core.db.dao.ProcessedTaskDao
import com.nazmiev.radik.vkclient.core.db.dao.ProcessedUserDao
import com.nazmiev.radik.vkclient.core.db.dao.ProxyAccountDao
import com.nazmiev.radik.vkclient.core.db.dao.ProxyListDao
import com.nazmiev.radik.vkclient.core.db.dao.ResponseMessageDao
import com.nazmiev.radik.vkclient.core.db.dao.ScriptDao
import com.nazmiev.radik.vkclient.core.db.dao.SearchSettingDao
import com.nazmiev.radik.vkclient.core.db.dao.SelectedAccountForScriptDao
import com.nazmiev.radik.vkclient.core.db.dao.SelectedAccountSelectedChatDao
import com.nazmiev.radik.vkclient.core.db.dao.SelectedAccountsForTaskDao
import com.nazmiev.radik.vkclient.core.db.dao.SentInvitationsDao
import com.nazmiev.radik.vkclient.core.db.dao.TaskDao
import com.nazmiev.radik.vkclient.core.db.dao.TaskHistoryDao
import com.nazmiev.radik.vkclient.core.db.dao.TaskProgressDao
import com.nazmiev.radik.vkclient.core.db.dao.UserAgentDao
import com.nazmiev.radik.vkclient.core.db.dao.UserDao
import com.nazmiev.radik.vkclient.core.db.dao.UserSettingDao
import com.nazmiev.radik.vkclient.core.db.dao.VkGroupDao
import com.nazmiev.radik.vkclient.core.db.dao.WelcomeMessageDao
import com.nazmiev.radik.vkclient.core.db.models.AccountFolder
import com.nazmiev.radik.vkclient.core.db.models.AccountFolderRelation
import com.nazmiev.radik.vkclient.core.db.models.AccountStatistic
import com.nazmiev.radik.vkclient.core.db.models.Cookie
import com.nazmiev.radik.vkclient.core.db.models.Foaf
import com.nazmiev.radik.vkclient.core.db.models.GlobalSetting
import com.nazmiev.radik.vkclient.core.db.models.GroupChatMessage
import com.nazmiev.radik.vkclient.core.db.models.GroupSearchSetting
import com.nazmiev.radik.vkclient.core.db.models.GroupsBlackList
import com.nazmiev.radik.vkclient.core.db.models.GroupsForPosting
import com.nazmiev.radik.vkclient.core.db.models.GroupsForProcessing
import com.nazmiev.radik.vkclient.core.db.models.LinkToUsers
import com.nazmiev.radik.vkclient.core.db.models.LinkToUsersFromFile
import com.nazmiev.radik.vkclient.core.db.models.Message
import com.nazmiev.radik.vkclient.core.db.models.MessagesAttachments
import com.nazmiev.radik.vkclient.core.db.models.PlanningPostComment
import com.nazmiev.radik.vkclient.core.db.models.PlanningPostGroups
import com.nazmiev.radik.vkclient.core.db.models.PlanningPostSetting
import com.nazmiev.radik.vkclient.core.db.models.PostingAccountWallSetting
import com.nazmiev.radik.vkclient.core.db.models.PrivateMessage
import com.nazmiev.radik.vkclient.core.db.models.ProcessedTask
import com.nazmiev.radik.vkclient.core.db.models.ProcessedUser
import com.nazmiev.radik.vkclient.core.db.models.ProxyAccount
import com.nazmiev.radik.vkclient.core.db.models.ProxyList
import com.nazmiev.radik.vkclient.core.db.models.ResponseMessage
import com.nazmiev.radik.vkclient.core.db.models.Script
import com.nazmiev.radik.vkclient.core.db.models.SearchSetting
import com.nazmiev.radik.vkclient.core.db.models.SelectedAccountForScript
import com.nazmiev.radik.vkclient.core.db.models.SelectedAccountForTask
import com.nazmiev.radik.vkclient.core.db.models.SelectedAccountSelectedChat
import com.nazmiev.radik.vkclient.core.db.models.SentInvitations
import com.nazmiev.radik.vkclient.core.db.models.Task
import com.nazmiev.radik.vkclient.core.db.models.TaskHistory
import com.nazmiev.radik.vkclient.core.db.models.TaskProgress
import com.nazmiev.radik.vkclient.core.db.models.User
import com.nazmiev.radik.vkclient.core.db.models.UserAgent
import com.nazmiev.radik.vkclient.core.db.models.UserSetting
import com.nazmiev.radik.vkclient.core.db.models.VkGroup
import com.nazmiev.radik.vkclient.core.db.models.WelcomeMessage

@Database(
    entities = [
        TaskHistory::class,
        User::class,
        SelectedAccountForTask::class,
        UserAgent::class,
        ProxyAccount::class,
        ProxyList::class,
        GlobalSetting::class,
        AccountFolder::class,
        AccountFolderRelation::class,
        AccountStatistic::class,
        GroupChatMessage::class,
        GroupSearchSetting::class,
        GroupsForPosting::class,
        GroupsForProcessing::class,
        GroupsBlackList::class,
        LinkToUsers::class,
        LinkToUsersFromFile::class,
        Message::class,
        MessagesAttachments::class,
        PlanningPostComment::class,
        PlanningPostGroups::class,
        PlanningPostSetting::class,
        PostingAccountWallSetting::class,
        PrivateMessage::class,
        ProcessedTask::class,
        ProcessedUser::class,
        ResponseMessage::class,
        Script::class,
        SearchSetting::class,
        SelectedAccountSelectedChat::class,
        SelectedAccountForScript::class,
        SentInvitations::class,
        TaskProgress::class,
        Task::class,
        UserSetting::class,
        VkGroup::class,
        WelcomeMessage::class,
        Foaf::class,
        Cookie::class
    ],
    version = 56,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun taskHistoryDao(): TaskHistoryDao

    abstract fun userDao(): UserDao

    abstract fun selectedAccountsForTaskDao(): SelectedAccountsForTaskDao

    abstract fun userAgentDao(): UserAgentDao

    abstract fun proxyAccountDao(): ProxyAccountDao

    abstract fun proxyListDao(): ProxyListDao

    abstract fun globalSettingDao(): GlobalSettingDao

    abstract fun accountFolderDao(): AccountFolderDao

    abstract fun accountFolderRelationDao(): AccountFolderRelationDao

    abstract fun accountStatisticDao(): AccountStatisticDao

    abstract fun cookieDao(): CookieDao

    abstract fun groupChatMessageDao(): GroupChatMessageDao

    abstract fun groupsBlackListDao(): GroupsBlackListDao

    abstract fun groupSearchSettingDao(): GroupSearchSettingDao

    abstract fun groupsForPostingDao(): GroupsForPostingDao

    abstract fun groupsForProcessingDao(): GroupsForProcessingDao

    abstract fun linkToUsersDao(): LinkToUsersDao

    abstract fun linkToUsersFromFileDao(): LinkToUsersFromFileDao

    abstract fun messageDao(): MessageDao

    abstract fun messageAttachmentsDao(): MessagesAttachmentsDao

    abstract fun planningPostComment(): PlanningPostCommentDao

    abstract fun planningPostGroupsDao(): PlanningPostGroupsDao

    abstract fun planningPostSettingDao(): PlanningPostSettingDao

    abstract fun postingAccountWallSettingDao(): PostingAccountWallSettingDao

    abstract fun privateMessageDao(): PrivateMessageDao

    abstract fun processedTaskDao(): ProcessedTaskDao

    abstract fun processedUserDao(): ProcessedUserDao

    abstract fun responseMessageDao(): ResponseMessageDao

    abstract fun scriptDao(): ScriptDao

    abstract fun searchSettingDao(): SearchSettingDao

    abstract fun selectedAccountSelectedChat(): SelectedAccountSelectedChatDao

    abstract fun selectedAccountForScriptDao(): SelectedAccountForScriptDao

    abstract fun sentInvitationsDao(): SentInvitationsDao

    abstract fun taskProgressDao(): TaskProgressDao

    abstract fun taskDao(): TaskDao

    abstract fun userSettingDao(): UserSettingDao

    abstract fun vkGroupDao(): VkGroupDao

    abstract fun welcomeMessageDao(): WelcomeMessageDao

    abstract fun foafDao(): FoafDao
}