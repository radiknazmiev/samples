package com.nazmiev.radik.vkclient.core.db

import android.app.Application
import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DbHelper @Inject constructor(@ApplicationContext app: Context) {

    private val CREATE_TABLE_USERS = ("CREATE TABLE if not exists users " +
            "(`_id` INTEGER NOT NULL, " +
            "acc_tocken text," +
            "last_search text," +
            "is_def INTEGER," +
            "login text," +
            "requests_count INTEGER," +
            "incomplite_search text," +
            "skip_processing INTEGER," +
            "acc_title text," +
            "is_partner_acc INTEGER," +
            "sort_index INTEGER," +
            "last_liking text default '01.01.1900 00:00:00'," +
            "last_message text default '01.01.1900 00:00:00'," +
            "account_login text," +
            "account_password text," +
            "updated_at text, PRIMARY KEY(`_id`));")

    private val CREATE_TABLE_USER_SETTINGS =
        ("create table if not exists " + TABLE_USER_SETTINGS + " ("
                + KEY_ID + " integer primary key autoincrement,"
                + KEY_ACC_TOCKEN + " text,"
                + KEY_COUNTRY + " INTEGER,"
                + KEY_CITY + " INTEGER,"
                + KEY_CITY_NAME + " text,"
                + KEY_AGE_FROM + " INTEGER,"
                + KEY_AGE_TO + " INTEGER,"
                + KEY_SEX + " INTEGER,"
                + KEY_IS_ONLINE + " INTEGER,"
                + KEY_MONTH_BIRTH + " INTEGRE,"
                + KEY_YEAR_BIRTH + " INTEGRE,"
                + KEY_MAX_REQ + " INTEGRE,"
                + KEY_GROUP + " INTEGRE,"
                + KEY_TRANSMITTAL_MESSAGE + " TEXT,"
                + KEY_IS_SEND_TRANSMITTAL_MESSAGE + " INTEGRE,"
                + KEY_BIRTH_DAY + " INTEGRE,"
                + KEY_MAX_MESSAGES + " INTEGRE,"
                + KEY_MAX_MESSAGES_AT_TIME + " INTEGRE,"
                + KEY_MAX_REQUESTS_AT_TIME + " INTEGRE,"
                + KEY_USER_ID + " INTEGER" + ");")

    private val CREATE_TABLE_MESSAGES =
        ("create table if not exists " + TABLE_MESSAGES + " ("
                + KEY_ID + " integer primary key autoincrement,"
                + KEY_USER_ID + " INTEGER,"
                + KEY_MESSAGE_TEXT + " text,"
                + KEY_TITLE + " text,"
                + KEY_TYPE + " INTEGER" + ");")

    private val CREATE_TABLE_MESSAGES_ATTACHMENTS =
        ("create table if not exists " + TABLE_MESSAGES_ATTACHMENTS + " ("
                + KEY_ID + " integer primary key autoincrement,"
                + KEY_MESSAGE_ID + " INTEGER,"
                + KEY_ATTACH_ID + " INTEGER,"
                + KEY_ATTACH_OWNER_ID + " INTEGER,"
                + KEY_ATTACH_SRC + " text,"
                + KEY_ACCESS_KEY + " text,"
                + KEY_ATTACH_TYPE + " text,"
                + KEY_COVER_URL + " text,"
                + KEY_FILE_PATH + " text" + ");")

    private val CREATE_TABLE_PARTNERS =
        ("create table if not exists " + TABLE_PARTNERS + " ("
                + KEY_PARTNER_CODE + " text,"
                + KEY_PARTNER_NAME + " text,"
                + KEY_PARTNER_ID + " text" + ");")
    private val CREATE_TABLE_DISTRIBUTION_LIST =
        ("create table if not exists " + TABLE_DISTRIBUTION_LIST + " ("
                + KEY_PARTNER_CODE + " text" + ");")

    private val CREATE_TABLE_PROXY_LIST =
        ("create table if not exists " + TABLE_PROXY_LIST + " ("
                + KEY_ID + " integer primary key autoincrement,"
                + KEY_HOSTNAME + " text,"
                + KEY_PORT + " integer,"
                + KEY_PROXY_LOGIN + " text,"
                + KEY_PROXY_PASSWORD + " text,"
                + KEY_IS_DEF + " integer,"
                + KEY_ACCOUNT + " integer" + ");")

    private val CREATE_TABLE_PROXY_TO_ACCAUNT =
        ("create table if not exists " + TABLE_PROXY_ACCOUNT + " ("
                + KEY_ID + " integer primary key autoincrement,"
                + KEY_ACCOUNT + " integer,"
                + KEY_PROXY + " integer" + ");")

    private val CREATE_TABLE_SEARCH_SETTINGS =
        ("create table if not exists " + TABLE_SEARCH_SETTINGS + " ("
                + KEY_ID + " integer primary key autoincrement,"
                + KEY_ACCOUNT + " integer,"
                + KEY_SEARCH_SOURCE + " integer,"
                + KEY_INVITE_TO_GROUP + " integer,"
                + KEY_GROUP_LINK + " integer,"
                + KEY_SENT_MESSAGE + " integer,"
                + KEY_ATTRACTION_SOURCE + " integer,"
                + KEY_SORT + " integer" + ");")

    private val CREATE_TABLE_SENT_INVITATIONS =
        ("create table if not exists " + TABLE_SENT_INVITATIONS + " ("
                + KEY_ID + " integer primary key autoincrement,"
                + KEY_USER_ID + " integer,"
                + KEY_INVITE_SENT_DATE + " text" + ");")

    private val CREATE_TABLE_WELCOM_MESSAGE =
        ("create table if not exists " + TABLE_WELCOME_MESSAGE + " ("
                + KEY_ID + " integer primary key autoincrement,"
                + KEY_USER_ID + " integer,"
                + KEY_MESSAGE_ID + " integer" + ");")

    private val CREATE_TABLE_PRIVATE_MESSAGE =
        ("create table if not exists " + TABLE_PRIVATE_MESSAGE + " ("
                + KEY_ID + " integer primary key autoincrement,"
                + KEY_USER_ID + " integer,"
                + KEY_MESSAGE_ID + " integer" + ");")

    private val CREATE_TABLE_RESPONSE_MESSAGE =
        ("create table if not exists " + TABLE_RESPONSE_MESSAGE + " ("
                + KEY_ID + " integer primary key autoincrement,"
                + KEY_INCOMING_PHRASE + " text,"
                + KEY_MESSAGE_ID + " integer, "
                + KEY_PREVIOUS_MESSAGE + " integer, "
                + KEY_PROCESSED_TEXT + " text, "
                + KEY_MATCH_PERCENTAGE + " integer" + ");")

    private val CREATE_TABLE_VK_GROUPS = ("create table if not exists " + TABLE_VK_GROUPS + " ("
            + KEY_ID + " integer primary key autoincrement,"
            + KEY_USER_ID + " integer,"
            + KEY_GROUP + " integer" + ");")

    private val CREATE_TABLE_LINK_TO_USER =
        ("create table if not exists " + TABLE_LINK_TO_USERS + " ("
                + KEY_ID + " integer primary key autoincrement,"
                + KEY_USER_ID + " integer,"
                + KEY_VK_USER_ID + " integer" + ");")

    private val CREATE_TABLE_LINK_TO_USER_FROM_FILE =
        ("create table if not exists " + TABLE_LINK_TO_USERS_FROM_FILE + " ("
                + KEY_ID + " integer primary key autoincrement,"
                + KEY_USER_ID + " integer,"
                + KEY_VK_USER_ID + " integer" + ");")

    private val CREATE_TABLE_TASKS = ("create table if not exists " + TABLE_TASKS + " ("
            + KEY_ID + " integer primary key autoincrement,"
            + KEY_NAME + " text,"
            + KEY_TYPE + " integer,"
            + KEY_SHEDULED_START_TIME + " text,"
            + KEY_STATUS + " integer" + ");")

    private val CREATE_TABLE_SELECTED_ACCOUNTS_FOR_TASK =
        ("CREATE TABLE if not exists selected_accounts_for_task (`id` INTEGER NOT NULL, task_id integer NOT NULL, accaunt integer NOT NULL, PRIMARY KEY(`id`))")

    private val CREATE_TABLE_ACCOUNT_SELECTED_CHATS =
        ("create table if not exists " + TABLE_ACCOUNT_SELECTED_CHATS + " ("
                + KEY_CHAT_ID + " integer,"
                + KEY_ACCOUNT + " integer" + ");")

    private val CREATE_TABLE_GROUP_CHAT_MESSAGE =
        ("create table if not exists " + TABLE_GROUP_CHAT_MESSAGE + " ("
                + KEY_ID + " integer primary key autoincrement,"
                + KEY_USER_ID + " integer,"
                + KEY_MESSAGE_ID + " integer" + ");")

    private val CREATE_TABLE_GROUP_SEARCH_SETTINGS =
        ("create table if not exists " + TABLE_GROUP_SEARCH_SETTINGS + " ("
                + KEY_ID + " integer primary key autoincrement,"
                + KEY_USER_ID + " integer,"
                + KEY_SEARCH_SOURCE + " integer,"
                + KEY_COUNTRY + " integer,"
                + KEY_CITY + " integer,"
                + KEY_PHRASE + " text,"
                + KEY_SORT + " integer,"
                + KEY_GROUP_COUNT_FROM + " integer,"
                + KEY_GROUP_COUNT_TO + " integer,"
                + KEY_MESSAGE_ID + " integer,"
                + KEY_GROUP_TYPE + " integer,"
                + KEY_AUTO_LIKING_POST + " integer,"
                + KEY_FILED_POST + " integer,"
                + KEY_PRE_JOIN_GROUP + " integer,"
                + KEY_SETTING_TITLE + " text" + ");")

    private val CREATE_TABLE_USER_AGENTS =
        ("create table if not exists " + TABLE_USER_AGENTS + " ("
                + KEY_ID + " integer primary key autoincrement,"
                + KEY_USER_ID + " integer,"
                + KEY_USER_AGENT + " text" + ");")

    private val CREATE_TABLE_GROUPS_FOR_POSTING =
        ("create table if not exists " + TABLE_GROUPS_FOR_POSTING + " ("
                + KEY_PID + " integer,"
                + KEY_GROUP + " integer" + ");")

    private val loadDefaultUserAgents = "insert into user_agents (user_agent) \n" +
        "select * from \n" +
        "( \n" +
        "select 'Mozilla/5.0 (Linux; U; Android 2.2) AppleWebKit/533.1 (KHTML, like Gecko) Version/4.0 Mobile Safari/533.1' \n" +
        "union all \n" +
        "select 'Mozilla/5.0 (Linux; Android 6.0.1; RedMi Note 5 Build/RB3N5C; wv) AppleWebKit/537.36 (KHTML, like Gecko) Version/4.0 Chrome/68.0.3440.91 Mobile Safari/537.36' \n" +
        "union all \n" +
        "select 'Mozilla/5.0 (Linux; Android 7.1.2; AFTMM Build/NS6265; wv) AppleWebKit/537.36 (KHTML, like Gecko) Version/4.0 Chrome/70.0.3538.110 Mobile Safari/537.36' \n" +
        "union all \n" +
        "select 'Mozilla/5.0 (Linux; Android 6.0; CAM-L21 Build/HUAWEICAM-L21; wv) AppleWebKit/537.36 (KHTML, like Gecko) Version/4.0 Chrome/62.0.3202.84 Mobile Safari/537.36' \n" +
        "union all \n" +
        "select 'Dalvik/1.6.0 (Linux; U; Android 4.1.1; BroadSign Xpress 1.0.14 B- (720) Build/JRO03H)' \n" +
        "union all \n" +
        "select 'Mozilla/5.0 (Linux; Android 7.0;) AppleWebKit/537.36 (KHTML, like Gecko) Mobile Safari/537.36 (compatible; AspiegelBot)' \n" +
        "union all \n" +
        "select 'Mozilla/5.0 (Linux; Android 5.1; HUAWEI CUN-L22 Build/HUAWEICUN-L22; wv) AppleWebKit/537.36 (KHTML, like Gecko) Version/4.0 Chrome/62.0.3202.84 Mobile Safari/537.36' \n" +
        "union all \n" +
        "select 'Mozilla/5.0 (Linux; Android 6.0.1; SM-J700M Build/MMB29K) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/67.0.3396.87 Mobile Safari/537.36' \n" +
        "union all \n" +
        "select 'Mozilla/5.0 (Linux; Android 7.0; SM-G610M Build/NRD90M) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/68.0.3440.91 Mobile Safari/537.36' \n" +
        "union all \n" +
        "select 'Mozilla/5.0 (Linux; Android 7.0; TRT-LX3 Build/HUAWEITRT-LX3) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/69.0.3497.100 Mobile Safari/537.36' \n" +
        "union all \n" +
        "select 'Mozilla/5.0 (Linux; Android 9; SAMSUNG SM-G955U) AppleWebKit/537.36 (KHTML, like Gecko) SamsungBrowser/11.1 Chrome/75.0.3770.143 Mobile Safari/537.36' \n" +
        "union all \n" +
        "select 'Mozilla/5.0 (Linux; Android 9; LM-X220) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/74.0.3729.136 Mobile Safari/537.36' \n" +
        "union all \n" +
        "select 'Mozilla/5.0 (Linux; Android 6.0.1; SM-J500M Build/MMB29M) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/65.0.3325.109 Mobile Safari/537.36' \n" +
        "union all \n" +
        "select 'Mozilla/5.0 (Linux; U; Android 4.1.2; de-de; GT-I9100 Build/JZO54K) AppleWebKit/534.30 (KHTML, like Gecko) Version/4.0 Mobile Safari/534.30' \n" +
        "union all \n" +
        "select 'Mozilla/5.0 (Linux; Android 6.0.1; SAMSUNG SM-G900F Build/MMB29M) AppleWebKit/537.36 (KHTML, like Gecko) SamsungBrowser/4.0 Chrome/44.0.2403.133 Mobile Safari/537.36' \n" +
        "union all \n" +
        "select 'Mozilla/5.0 (Linux; Android 9; SM-A102U) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/78.0.3904.108 Mobile Safari/537.36' \n" +
        "union all \n" +
        "select 'Mozilla/5.0 (Linux; Android 9; SAMSUNG SM-G960U Build/PPR1.180610.011) AppleWebKit/537.36 (KHTML, like Gecko) SamsungBrowser/9.4 Chrome/67.0.3396.87 Mobile Safari/537.36' \n" +
        "union all \n" +
        "select 'Mozilla/5.0 (Linux; Android 7.1.1; Moto G (5S) Build/NPPS26.102-49-14) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/69.0.3497.100 Mobile Safari/537.36' \n" +
        "union all \n" +
        "select 'Mozilla/5.0 (Linux; Android 8.0.0; SAMSUNG SM-G955F Build/R16NW) AppleWebKit/537.36 (KHTML, like Gecko) SamsungBrowser/7.4 Chrome/59.0.3071.125 Mobile Safari/537.36' \n" +
        "union all \n" +
        "select 'Mozilla/5.0 (Linux; Android 8.1.0; Moto G (5S) Plus) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/70.0.3538.80 Mobile Safari/537.36' \n" +
        "union all \n" +
        "select 'Mozilla/5.0 (Linux; U; Android 4.2.2; de-de; SM-T110 Build/JDQ39) AppleWebKit/534.30 (KHTML, like Gecko) Version/4.0 Safari/534.30' \n" +
        "union all \n" +
        "select 'Mozilla/5.0 (Linux; Android 7.0; HUAWEI VNS-L23 Build/HUAWEIVNS-L23) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/69.0.3497.100 Mobile Safari/537.36' \n" +
        "union all \n" +
        "select 'Mozilla/5.0 (Linux; Android 4.4.2; LG-D405 Build/KOT49I.A1404477143) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/42.0.2311.111 Mobile Safari/537.36' \n" +
        "union all \n" +
        "select 'Mozilla/5.0 (Linux; Android 9; SM-G955U) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/77.0.3865.92 Mobile Safari/537.36' \n" +
        "union all \n" +
        "select 'Mozilla/5.0 (Linux; Android 9; SAMSUNG SM-G960U Build/PPR1.180610.011) AppleWebKit/537.36 (KHTML, like Gecko) SamsungBrowser/9.2 Chrome/67.0.3396.87 Mobile Safari/537.36' \n" +
        "union all \n" +
        "select 'Mozilla/5.0 (Linux; Android 9; SAMSUNG SM-G960F Build/PPR1.180610.011) AppleWebKit/537.36 (KHTML, like Gecko) SamsungBrowser/9.2 Chrome/67.0.3396.87 Mobile Safari/537.36' \n" +
        "union all \n" +
        "select 'Mozilla/5.0 (Linux; Android 9; SM-G973F Build/PPR1.180610.011; wv) AppleWebKit/537.36 (KHTML, like Gecko) Version/4.0 Chrome/74.0.3729.157 Mobile Safari/537.36' \n" +
        "union all \n" +
        "select 'Mozilla/5.0 (Linux; Android 9; SAMSUNG SM-G950F Build/PPR1.180610.011) AppleWebKit/537.36 (KHTML, like Gecko) SamsungBrowser/9.2 Chrome/67.0.3396.87 Mobile Safari/537.36' \n" +
        "union all \n" +
        "select 'Mozilla/5.0 (Linux; Android 10; SAMSUNG SM-N975U) AppleWebKit/537.36 (KHTML, like Gecko) SamsungBrowser/11.1 Chrome/75.0.3770.143 Mobile Safari/537.36' \n" +
        "union all \n" +
        "select 'Mozilla/5.0 (Linux; Android 5.1.1; SAMSUNG SM-J500FN Build/LMY48B) AppleWebKit/537.36 (KHTML, like Gecko) SamsungBrowser/3.3 Chrome/38.0.2125.102 Mobile Safari/537.36' \n" +
        "union all \n" +
        "select 'Mozilla/5.0 (Linux; Android 8.0.0; SAMSUNG SM-A520F Build/R16NW) AppleWebKit/537.36 (KHTML, like Gecko) SamsungBrowser/7.4 Chrome/59.0.3071.125 Mobile Safari/537.36' \n" +
        "union all \n" +
        "select 'Mozilla/5.0 (Linux; Android 9; moto g(7) play) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/73.0.3683.90 Mobile Safari/537.36' \n" +
        "union all \n" +
        "select 'Mozilla/5.0 (Linux; Android 9; SAMSUNG SM-G950U Build/PPR1.180610.011) AppleWebKit/537.36 (KHTML, like Gecko) SamsungBrowser/9.4 Chrome/67.0.3396.87 Mobile Safari/537.36' \n" +
        "union all \n" +
        "select 'Mozilla/5.0 (Linux; Android 9; LM-Q720) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/81.0.4044.138 Mobile Safari/537.36' \n" +
        ") \n" +
        "where not exists(select * from user_agents) \n"

    private val CREATE_TABLE_GROUPS_BLACK_LIST =
        ("create table if not exists " + TABLE_GROUPS_BLACK_LIST + " ("
                + KEY_GROUP + " integer" + ");")

    private val CREATE_TABLE_COOKIES = ("create table if not exists " + TABLE_COOKIES + " ("
            + KEY_USER_ID + " integer,"
            + KEY_USER_AGENT + " integer,"
            + KEY_COOKIE_NAME + " text,"
            + KEY_COOKIE_VALUE + " text" + ");")

    private val CREATE_TABLE_PLANNING_POST_SETTINGS =
        ("create table if not exists " + TABLE_PLANNING_POST_SETTINGS + " ("
                + KEY_ID + " integer primary key autoincrement,"
                + KEY_POST_ID + " integer,"
                + KEY_USER_ID + " integer,"
                + KEY_USER_NAME + " text,"
                + KEY_PLANNING_DATE_TIME + " text,"
                + KEY_REPOST + " integer,"
                + KEY_SET_LIKE + " integer,"
                + KEY_PLANNING_POST_MESSAGE + " text,"
                + KEY_SET_COMMENT + " integer,"
                + KEY_COMMENT_PAUSE + " integer,"
                + KEY_POST_ON_TELEGRAM + " integer,"
                + KEY_TELEGRAM_CHATS + " text" + ");")

    private val CREATE_TABLE_PLANNING_POST_GROUPS =
        ("create table if not exists " + TABLE_PLANNING_POST_GROUPS + " ("
                + KEY_PLANNING_POST_ID + " integer,"
                + KEY_GROUP + " integer,"
                + KEY_GROUP_NAME + " text" + ");")

    private val CREATE_TABLE_PLANNING_POST_COMMENTS =
        ("create table if not exists " + TABLE_PLANNING_POST_COMMENTS + " ("
                + KEY_PLANNING_POST_ID + " integer,"
                + KEY_COMMENT_ID + " integer,"
                + KEY_COMMENT_NAME + " text" + ");")

    private val CREATE_TABLE_PROCESSED_USERS =
        ("create table if not exists " + TABLE_PROCESSED_USERS + " ("
                + KEY_USER_ID + " integer,"
                + KEY_OPERATION_TYPE + " integer" + ");")

    private val CREATE_TABLE_SCRIPTS = ("create table if not exists " + TABLE_SCRIPTS + " ("
            + KEY_ID + " integer primary key autoincrement,"
            + KEY_NAME + " text,"
            + KEY_TYPE + " integer,"
            + KEY_STATUS + " integer" + ");")

    private val CREATE_TABLE_SELECTED_ACCOUNTS_FOR_SCRIPT =
        ("create table if not exists " + TABLE_SELECTED_ACCOUNTS_FOR_SCRIPT + " ("
                + KEY_SCRIPT_ID + " integer,"
                + KEY_ACCOUNT + " integer" + ");")

    private val CREATE_TABLE_POSTING_ACCOUNT_WALL_SETTINGS =
        ("create table if not exists " + TABLE_POSTING_ACCOUNT_WALL_SETTINGS + " ("
                + KEY_SCRIPT_ID + " integer,"
                + KEY_POST_ID + " integer" + ");")

    private val CREATE_TABLE_ACCOUNT_STATISTIC =
        ("create table if not exists " + TABLE_ACCOUNT_STATISTIC + " ("
                + KEY_STATIC_DATE + " text,"
                + KEY_ACCOUNT_ID + " integer,"
                + KEY_FR_REQUEST_COUNT + " integer,"
                + KEY_MESSAGES_COUNT + " integer,"
                + KEY_LIKES_COUNT + " integer,"
                + KEY_POSTS_COUNT + " integer,"
                + KEY_UNFOLLOW_COUNT + " integer" + ");")

    private val CREATE_TABLE_TASK_PROGRESS =
        ("create table if not exists " + TABLE_TASK_PROGRESS + " ("
                + KEY_TYPE + " integer,"
                + KEY_ACCOUNT_NUMBER + " integer,"
                + KEY_CURRENT_REQUESTS_COUNT + " integer,"
                + KEY_LAST_DATE + " text,"
                + KEY_IS_FULL + " integer,"
                + KEY_IS_PAYED + " integer,"
                + KEY_THREAD_NUMBER + " integer,"
                + KEY_ACCOUNT + " integer" + ");")

    private val CREATE_TABLE_TASK_HISTORY =
        ("CREATE TABLE if not exists task_history (`id` INTEGER NOT NULL,task_type integer NOT NULL,history_text text NOT NULL,history_time text NOT NULL,current_d text NOT NULL,user_id integer NOT NULL,thread_number integer NOT NULL, PRIMARY KEY(`id`));")

    private val CREATE_TABLE_PROCESSED_TASK =
        ("create table if not exists " + TABLE_PROCESSED_TASK + " ("
                + KEY_TASK_ID + " integer,"
                + KEY_TASK_TYPE + " integer" + ");")

    private val CREATE_TABLE_GLOBAL_SETTINGS =
        ("create table if not exists " + TABLE_GLOBAL_SETTINGS + " ("
                + KEY_IS_START_TASK_MANAGER + " integer,"
                + KEY_NOTIF_SOUND_ACTIVATED + " text default 'true',"
                + KEY_NOTIF_VIBRATION_ACTIVATED + " text default 'true'" + ");")

    private val CREATE_TABLE_GROUPS_FOR_PROCESSING =
        ("create table if not exists " + TABLE_GROUPS_FOR_PROCESSING + " ("
                + KEY_PID + " integer,"
                + KEY_GROUP + " integer,"
                + KEY_IS_MEMBER + " integer" + ");")

    private val CREATE_TABLE_FOAF = ("create table if not exists " + TABLE_FOAF + " ("
            + KEY_USER_ID + " integer,"
            + KEY_CREATE_DATE + " text,"
            + KEY_MODIFIED_DATE + " text,"
            + KEY_LAST_LOGGED_IN + " integer" + ");")

    val CREATE_TABLE_ACCOUNT_FOLDERS = ("create table if not exists " + TABLE_ACCOUNT_FOLDERS + " ("
            + KEY_ID + " integer primary key autoincrement,"
            + KEY_NAME + " text" + ");")

    val CREATE_TABLE_ACCOUNT_FOLDER_RELATION = ("create table if not exists " + TABLE_ACCOUNT_FOLDER_RELATION + " ("
            + KEY_USER_ID + " integer,"
            + KEY_PID + " integer" + ");")


//    override fun onCreate(db: SQLiteDatabase?) {
//        db?.execSQL(CREATE_TABLE_USERS)
//        db?.execSQL(CREATE_TABLE_USER_SETTINGS)
//        db?.execSQL(CREATE_TABLE_MESSAGES)
//        db?.execSQL(CREATE_TABLE_MESSAGES_ATTACHMENTS)
//        db?.execSQL(CREATE_TABLE_PARTNERS)
//        db?.execSQL(CREATE_TABLE_DISTRIBUTION_LIST)
//        db?.execSQL(CREATE_TABLE_PROXY_LIST)
//        db?.execSQL(CREATE_TABLE_PROXY_TO_ACCAUNT)
//        db?.execSQL(CREATE_TABLE_SEARCH_SETTINGS)
//        db?.execSQL(CREATE_TABLE_SENT_INVITATIONS)
//        db?.execSQL(CREATE_TABLE_WELCOM_MESSAGE)
//        db?.execSQL(CREATE_TABLE_PRIVATE_MESSAGE)
//        db?.execSQL(CREATE_TABLE_RESPONSE_MESSAGE)
//        db?.execSQL(CREATE_TABLE_VK_GROUPS)
//        db?.execSQL(CREATE_TABLE_TASKS)
//        db?.execSQL(CREATE_TABLE_SELECTED_ACCOUNTS_FOR_TASK)
//        db?.execSQL(CREATE_TABLE_ACCOUNT_SELECTED_CHATS)
//        db?.execSQL(CREATE_TABLE_GROUP_CHAT_MESSAGE)
//        db?.execSQL(CREATE_TABLE_LINK_TO_USER)
//        db?.execSQL(CREATE_TABLE_LINK_TO_USER_FROM_FILE)
//        db?.execSQL(CREATE_TABLE_GROUP_SEARCH_SETTINGS)
//        db?.execSQL(CREATE_TABLE_GROUPS_FOR_POSTING)
//        db?.execSQL(CREATE_TABLE_USER_AGENTS)
//        db?.execSQL(loadDefaultUserAgents)
//        db?.execSQL(CREATE_TABLE_GROUPS_BLACK_LIST)
//        db?.execSQL(CREATE_TABLE_COOKIES)
//        db?.execSQL(CREATE_TABLE_PLANNING_POST_SETTINGS)
//        db?.execSQL(CREATE_TABLE_PLANNING_POST_GROUPS)
//        db?.execSQL(CREATE_TABLE_PLANNING_POST_COMMENTS)
//        db?.execSQL(CREATE_TABLE_PROCESSED_USERS)
//        db?.execSQL(CREATE_TABLE_SCRIPTS)
//        db?.execSQL(CREATE_TABLE_SELECTED_ACCOUNTS_FOR_SCRIPT)
//        db?.execSQL(CREATE_TABLE_POSTING_ACCOUNT_WALL_SETTINGS)
//        db?.execSQL(CREATE_TABLE_ACCOUNT_STATISTIC)
//        db?.execSQL(CREATE_TABLE_TASK_PROGRESS)
//        db?.execSQL(CREATE_TABLE_TASK_HISTORY)
//        db?.execSQL(CREATE_TABLE_PROCESSED_TASK)
//        db?.execSQL(CREATE_TABLE_GLOBAL_SETTINGS)
//        db?.execSQL(CREATE_TABLE_GROUPS_FOR_PROCESSING)
//        db?.execSQL(CREATE_TABLE_FOAF)
//        db?.execSQL(CREATE_TABLE_ACCOUNT_FOLDERS)
//        db?.execSQL(CREATE_TABLE_ACCOUNT_FOLDER_RELATION)
//    }

//    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
//        onCreate(db)
//        try {
//            db?.execSQL("delete from private_message where not EXISTS (select 1 from messages m where m._id = private_message.message_id)")
//            db?.execSQL("delete from group_chat_message where not EXISTS (select 1 from messages m where m._id = group_chat_message.message_id)")
//            db?.execSQL("delete from private_message where not EXISTS (select 1 from users m where m._id = private_message.user_id)")
//            db?.execSQL("delete from group_chat_message where not EXISTS (select 1 from users m where m._id = group_chat_message.user_id)")
//        } catch (ex: Exception) {
//            ex.printStackTrace()
//        }
//        if (oldVersion <= 25) {
//            val cv = ContentValues()
//            cv.put(KEY_SKIP_PROCESSING, "0")
//            db?.update(TABLE_NAME, cv, "$KEY_SKIP_PROCESSING = 1", null)
//            db?.execSQL("update search_settings set search_source = 2 where search_source = 1")
//        }
//        if (oldVersion <= 26) {
//            db?.execSQL("alter table $TABLE_USER_SETTINGS ADD COLUMN $KEY_BIRTH_DAY INTEGER")
//        }
//        if (oldVersion <= 27) {
//            db?.execSQL("alter table $TABLE_GROUP_SEARCH_SETTINGS ADD COLUMN $KEY_AUTO_LIKING_POST INTEGER default 0")
//        }
//        if (oldVersion <= 28) {
//            db?.execSQL("alter table $TABLE_GROUP_SEARCH_SETTINGS ADD COLUMN $KEY_FILED_POST INTEGER default 0")
//            db?.execSQL("alter table $TABLE_GROUP_SEARCH_SETTINGS ADD COLUMN $KEY_PRE_JOIN_GROUP INTEGER default 0")
//        }
//        if (oldVersion <= 29) {
//            db?.execSQL("alter table $TABLE_USER_SETTINGS ADD COLUMN $KEY_MAX_MESSAGES INTEGER default 0")
//        }
//        if (oldVersion < 31) {
//            db?.execSQL("alter table $TABLE_MESSAGES ADD COLUMN $KEY_TYPE INTEGER default 1")
//            db?.execSQL("update messages set \"type\" = 2 where _id in (select gss.message_id from group_search_setting gss)")
//        }
//        if (oldVersion < 33) {
//            try {
//                db?.execSQL("alter table $TABLE_PLANNING_POST_SETTINGS ADD COLUMN $KEY_PLANNING_POST_MESSAGE text")
//                db?.execSQL("alter table $TABLE_PLANNING_POST_SETTINGS ADD COLUMN $KEY_SET_COMMENT INTEGER default 0")
//                db?.execSQL("alter table $TABLE_PLANNING_POST_SETTINGS ADD COLUMN $KEY_COMMENT_PAUSE INTEGER default 10")
//            } catch (ex: Exception) {
//                ex.printStackTrace()
//            }
//        }
//        if (oldVersion < 37) {
//            try {
//                db?.execSQL("alter table $TABLE_ACCOUNT_STATISTIC ADD COLUMN $KEY_UNFOLLOW_COUNT INTEGER")
//            } catch (ex: Exception) {
//                ex.printStackTrace()
//            }
//        }
//        if (oldVersion < 38) {
//            try {
//                db?.execSQL("alter table $TABLE_PLANNING_POST_SETTINGS ADD COLUMN $KEY_POST_ON_TELEGRAM integer")
//                db?.execSQL("alter table $TABLE_PLANNING_POST_SETTINGS ADD COLUMN $KEY_TELEGRAM_CHATS text")
//            } catch (ex: Exception) {
//                ex.printStackTrace()
//            }
//        }
//        if (oldVersion < 40) {
//            try {
//                db?.execSQL("alter table $TABLE_TASK_PROGRESS ADD COLUMN $KEY_THREAD_NUMBER integer")
//            } catch (ex: Exception) {
//                ex.printStackTrace()
//            }
//        }
//        if (oldVersion < 43) {
//            try {
//                db?.execSQL("alter table $TABLE_RESPONSE_MESSAGE ADD COLUMN $KEY_PROCESSED_TEXT text")
//            } catch (ex: Exception) {
//                ex.printStackTrace()
//            }
//        }
//        if (oldVersion < 44) {
//            try {
//                db?.execSQL("alter table $TABLE_USER_SETTINGS ADD COLUMN $KEY_MAX_MESSAGES_AT_TIME integer")
//            } catch (ex: Exception) {
//                ex.printStackTrace()
//            }
//            try {
//                db?.execSQL("alter table $TABLE_NAME ADD COLUMN $KEY_LAST_MESSAGE text default '01.01.1900 00:00:00'")
//            } catch (ex: Exception) {
//                ex.printStackTrace()
//            }
//            try {
//                db?.execSQL("alter table $TABLE_USER_SETTINGS ADD COLUMN $KEY_MAX_REQUESTS_AT_TIME integer")
//            } catch (ex: Exception) {
//                ex.printStackTrace()
//            }
//        }
//        if (oldVersion < 45) {
//            try {
//                db?.execSQL("alter table $TABLE_GLOBAL_SETTINGS ADD COLUMN $KEY_NOTIF_SOUND_ACTIVATED text default 'true'")
//            } catch (ex: Exception) {
//                ex.printStackTrace()
//            }
//            try {
//                db?.execSQL("alter table $TABLE_GLOBAL_SETTINGS ADD COLUMN $KEY_NOTIF_VIBRATION_ACTIVATED text default 'true'")
//            } catch (ex: Exception) {
//                ex.printStackTrace()
//            }
//        }
//        if (oldVersion < 47) {
//            try {
//                db?.execSQL("alter table $TABLE_NAME ADD COLUMN $KEY_ACCOUNT_LOGIN text")
//            } catch (ex: Exception) {
//                ex.printStackTrace()
//            }
//            try {
//                db?.execSQL("alter table $TABLE_NAME ADD COLUMN $KEY_ACCOUNT_PASSWORD text")
//            } catch (ex: Exception) {
//                ex.printStackTrace()
//            }
//        }
//
//        if (oldVersion < 48) {
//            try {
//                db?.execSQL("alter table $TABLE_TASK_PROGRESS ADD COLUMN $KEY_ACCAUNT integer")
//            } catch (ex: Exception) {
//                ex.printStackTrace()
//            }
//        }
//
//        if (oldVersion < 49) {
//            try {
//                db?.execSQL("alter table $TABLE_MESSAGES_ATTACHMENTS ADD COLUMN $KEY_FILE_PATH text")
//            } catch (ex: Exception) {
//                ex.printStackTrace()
//            }
//        }
//
//        if (oldVersion < 50) {
//            try {
//                db?.execSQL("alter table $TABLE_RESPONSE_MESSAGE ADD COLUMN $KEY_MATCH_PERCENTAGE INTEGER default 100")
//            } catch (ex: Exception) {
//                ex.printStackTrace()
//            }
//        }
//
//        if (oldVersion < 51) {
//            try {
//                db?.execSQL(
//                    "create table if not exists group_search_setting_temp ("
//                            + KEY_ID + " integer primary key autoincrement,"
//                            + KEY_USER_ID + " integer,"
//                            + KEY_SEARCH_SOURCE + " integer,"
//                            + KEY_COUNTRY + " integer,"
//                            + KEY_CITY + " integer,"
//                            + KEY_PHRASE + " text,"
//                            + KEY_SORT + " integer,"
//                            + KEY_GROUP_COUNT_FROM + " integer,"
//                            + KEY_GROUP_COUNT_TO + " integer,"
//                            + KEY_MESSAGE_ID + " integer,"
//                            + KEY_GROUP_TYPE + " integer,"
//                            + KEY_AUTO_LIKING_POST + " integer,"
//                            + KEY_FILED_POST + " integer,"
//                            + KEY_PRE_JOIN_GROUP + " integer,"
//                            + KEY_SETTING_TITLE + " text" + ");"
//                )
//                db?.execSQL(
//                    ("INSERT INTO group_search_setting_temp(user_id, search_source, country, city, phrase, sort, group_count_from, " +
//                            "group_count_to, message_id, group_type, auto_liking_post, filed_post, pre_join_group, setting_title) " +
//                            "SELECT " +
//                            " gss.user_id," +
//                            " gss.search_source," +
//                            " gss.country," +
//                            " gss.city," +
//                            " gss.phrase," +
//                            " gss.sort," +
//                            " gss.group_count_from," +
//                            " gss.group_count_to," +
//                            " gss.message_id," +
//                            " gss.group_type," +
//                            " gss.auto_liking_post," +
//                            " gss.filed_post," +
//                            " gss.pre_join_group, " +
//                            " 'Без названия'" +
//                            " from group_search_setting gss")
//                )
//                db?.execSQL("drop table group_search_setting")
//                db?.execSQL(CREATE_TABLE_GROUP_SEARCH_SETTINGS)
//                db?.execSQL(
//                    ("INSERT INTO group_search_setting(user_id, search_source, country, city, phrase, sort, group_count_from, " +
//                            "group_count_to, message_id, group_type, auto_liking_post, filed_post, pre_join_group, setting_title) " +
//                            "SELECT " +
//                            " gss.user_id," +
//                            " gss.search_source," +
//                            " gss.country," +
//                            " gss.city," +
//                            " gss.phrase," +
//                            " gss.sort," +
//                            " gss.group_count_from," +
//                            " gss.group_count_to," +
//                            " gss.message_id," +
//                            " gss.group_type," +
//                            " gss.auto_liking_post," +
//                            " gss.filed_post," +
//                            " gss.pre_join_group, " +
//                            " gss.setting_title" +
//                            " from group_search_setting_temp gss")
//                )
//                db?.execSQL("drop table group_search_setting_temp")
//                db?.execSQL(
//                    ("create table if not exists groups_for_posting_temp ("
//                            + KEY_PID + " integer,"
//                            + KEY_GROUP + " integer" + ");")
//                )
//                db?.execSQL("INSERT INTO groups_for_posting_temp(pid, group_id) select 1, gfp.group_id from groups_for_posting gfp")
//                db?.execSQL("drop table groups_for_posting")
//                db?.execSQL(CREATE_TABLE_GROUPS_FOR_POSTING)
//                db?.execSQL("INSERT INTO groups_for_posting(pid, group_id) select gfp.pid, gfp.group_id from groups_for_posting_temp gfp")
//                db?.execSQL("drop table groups_for_posting_temp")
//                db?.execSQL(
//                    ("create table if not exists groups_for_processing_temp ("
//                            + KEY_PID + " integer,"
//                            + KEY_GROUP + " integer,"
//                            + KEY_IS_MEMBER + " integer" + ");")
//                )
//                db?.execSQL("INSERT INTO groups_for_processing_temp(pid, group_id, is_member) select 1, gfp.group_id, gfp.is_member from groups_for_processing gfp")
//                db?.execSQL("drop table groups_for_processing")
//                db?.execSQL(CREATE_TABLE_GROUPS_FOR_PROCESSING)
//                db?.execSQL("INSERT INTO groups_for_processing(pid, group_id, is_member) select gfp.pid, gfp.group_id, gfp.is_member from groups_for_processing_temp gfp")
//                db?.execSQL("drop table groups_for_processing_temp")
//            } catch (ex: Exception) {
//                ex.printStackTrace()
//            }
//        }
//
//        if (oldVersion < 52){
////            val userSettings = UserSettings()
////            if (db != null) {
////                userSettings.migrate(db)
////            }
//        }
//
//        if (oldVersion < 53){
//            db?.beginTransaction()
//            db?.execSQL("ALTER TABLE users ADD COLUMN updated_at TEXT")
//            db?.execSQL("update users set updated_at = (select strftime('%d.%m.%Y %H:%M:%S'))")
//            db?.setTransactionSuccessful()
//            db?.endTransaction()
//        }
//
//        if (oldVersion < 62) {
//            db?.beginTransaction()
//            db?.execSQL("CREATE TABLE selected_accounts_for_task_tmp (task_id integer,accaunt integer);")
//            db?.execSQL("INSERT INTO selected_accounts_for_task_tmp(task_id, accaunt) select th.task_id, th.accaunt from selected_accounts_for_task th")
//            db?.execSQL("drop table selected_accounts_for_task")
//            db?.execSQL("CREATE TABLE if not exists selected_accounts_for_task (`id` INTEGER NOT NULL, task_id integer NOT NULL, accaunt integer NOT NULL, PRIMARY KEY(`id`))")
//            db?.execSQL("INSERT INTO selected_accounts_for_task(task_id, accaunt) select th.task_id, th.accaunt from selected_accounts_for_task_tmp th")
//            db?.execSQL("drop table selected_accounts_for_task_tmp")
//            db?.setTransactionSuccessful()
//            db?.endTransaction()
//        }
//    }

    companion object {
        private var mInstance: DbHelper? = null

        const val DATABASE_NAME = "usersDB"

        const val TABLE_NAME = "users"
        const val TABLE_USER_SETTINGS = "user_settings"
        const val TABLE_MESSAGES = "messages"
        const val TABLE_MESSAGES_ATTACHMENTS = "messages_attachments"
        const val TABLE_PARTNERS = "partners"
        const val TABLE_DISTRIBUTION_LIST = "distribution_list"
        const val TABLE_PROXY_LIST = "proxy_list"
        const val TABLE_PROXY_ACCOUNT = "proxy_account"
        const val TABLE_SEARCH_SETTINGS = "search_settings"
        const val TABLE_SENT_INVITATIONS = "sent_invitations"
        const val TABLE_WELCOME_MESSAGE = "welcome_message"
        const val TABLE_PRIVATE_MESSAGE = "private_message"
        const val TABLE_RESPONSE_MESSAGE = "response_message"
        const val TABLE_VK_GROUPS = "vk_groups"
        const val TABLE_LINK_TO_USERS = "link_to_users"
        const val TABLE_LINK_TO_USERS_FROM_FILE = "link_to_users_from_file"
        const val TABLE_TASKS = "tasks"
        const val TABLE_SCRIPTS = "scripts"
        const val TABLE_SELECTED_ACCOUNTS_FOR_TASK = "selected_accounts_for_task"
        const val TABLE_SELECTED_ACCOUNTS_FOR_SCRIPT = "selected_accounts_for_script"
        const val TABLE_ACCOUNT_SELECTED_CHATS = "selected_account_selected_chats"
        const val TABLE_GROUP_CHAT_MESSAGE = "group_chat_message"
        const val TABLE_GROUP_SEARCH_SETTINGS = "group_search_setting"
        const val TABLE_GROUPS_FOR_POSTING = "groups_for_posting"
        const val TABLE_USER_AGENTS = "user_agents"
        const val TABLE_GROUPS_BLACK_LIST = "groups_black_list"
        const val TABLE_COOKIES = "cookies"
        const val TABLE_PLANNING_POST_SETTINGS = "planning_post_settings"
        const val TABLE_PLANNING_POST_GROUPS = "planning_post_groups"
        const val TABLE_PLANNING_POST_COMMENTS = "planning_post_comments"
        const val TABLE_PROCESSED_USERS = "processed_users"
        const val TABLE_POSTING_ACCOUNT_WALL_SETTINGS = "posting_account_wall_settings"
        const val TABLE_ACCOUNT_STATISTIC = "account_statistic"
        const val TABLE_TASK_PROGRESS = "task_progress"
        const val TABLE_TASK_HISTORY = "task_history"
        const val TABLE_PROCESSED_TASK = "processed_task"
        const val TABLE_GLOBAL_SETTINGS = "global_settings"
        const val TABLE_GROUPS_FOR_PROCESSING = "groups_for_processing"
        const val TABLE_FOAF = "foaf"
        const val TABLE_ACCOUNT_FOLDERS = "account_folders"
        const val TABLE_ACCOUNT_FOLDER_RELATION = "account_folder_relation"

        const val KEY_ID = "_id"
        const val KEY_PID = "pid"
        const val KEY_ACC_TOCKEN = "access_token"
        const val KEY_LAST_SEARCH = "last_search"
        const val KEY_IS_DEF = "is_def"
        const val KEY_LOGIN = "login"
        const val KEY_COUNT_REQUESTS = "requests_count"
        const val KEY_INCOMPLETE_SEARCH = "incomplete_search"
        const val KEY_SKIP_PROCESSING = "skip_processing"
        const val KEY_IS_PARTNER_ACC = "is_partner_acc"
        const val KEY_ACC_TITLE = "acc_title"
        const val KEY_SORT_INDEX = "sort_index"
        const val KEY_LAST_LIKING = "last_liking"
        const val KEY_LAST_MESSAGE = "last_message"
        const val KEY_ACCOUNT_LOGIN = "account_login"
        const val KEY_ACCOUNT_PASSWORD = "account_password"
        const val KEY_UPDATED_AT = "updated_at"

        const val KEY_CITY = "city"
        const val KEY_CITY_NAME = "city_name"
        const val KEY_COUNTRY = "country"
        const val KEY_AGE_FROM = "age_from"
        const val KEY_AGE_TO = "age_to"
        const val KEY_SEX = "sex"
        const val KEY_IS_ONLINE = "is_online"
        const val KEY_MONTH_BIRTH = "month_birth"
        const val KEY_YEAR_BIRTH = "year_birth"
        const val KEY_MAX_REQ = "acc_max_requests"
        const val KEY_MAX_MESSAGES = "acc_max_messages"
        const val KEY_GROUP = "group_id"
        const val KEY_TRANSMITTAL_MESSAGE = "transmittal_message"
        const val KEY_IS_SEND_TRANSMITTAL_MESSAGE = "is_send_transmittal_message"
        const val KEY_BIRTH_DAY = "birth_day"
        const val KEY_MAX_MESSAGES_AT_TIME = "acc_max_messages_at_time"
        const val KEY_MAX_REQUESTS_AT_TIME = "acc_max_requests_at_time"
        const val KEY_USER_ID = "user_id"
        const val KEY_MESSAGE_TEXT = "message_text"
        const val KEY_MESSAGE_ID = "message_id"
        const val KEY_ATTACH_ID = "attach_id"
        const val KEY_ATTACH_OWNER_ID = "attach_owner_id"
        const val KEY_ATTACH_SRC = "attach_src"
        const val KEY_ACCESS_KEY = "access_key"
        const val KEY_ATTACH_TYPE = "attach_type"
        const val KEY_COVER_URL = "cover_url"
        const val KEY_TITLE = "title"
        const val KEY_PROCESSED_TEXT = "processed_text"
        const val KEY_FILE_PATH = "file_path"

        const val KEY_PARTNER_CODE = "partner_code"
        const val KEY_PARTNER_NAME = "partner_name"
        const val KEY_PARTNER_ID = "partner_id"

        const val KEY_HOSTNAME = "hostname"
        const val KEY_PORT = "port"
        const val KEY_PROXY_LOGIN = "proxy_login"
        const val KEY_PROXY_PASSWORD = "proxy_password"
        const val KEY_ACCOUNT = "account"
        const val KEY_PROXY = "proxy"

        const val KEY_SEARCH_SOURCE = "search_source"
        const val KEY_GROUP_LINK = "group_link"
        const val KEY_INVITE_TO_GROUP = "invite_to_group"
        const val KEY_SENT_MESSAGE = "sent_message"
        const val KEY_ATTRACTION_SOURCE = "attraction_source"
        const val KEY_SORT = "sort"
        const val KEY_VK_USER_ID = "vk_user_id"

        const val KEY_INVITE_SENT_DATE = "invite_sent_date"

        const val KEY_INCOMING_PHRASE = "incoming_phrase"
        const val KEY_PREVIOUS_MESSAGE = "previous_message"
        const val KEY_MATCH_PERCENTAGE = "match_percentage"

        const val KEY_NAME = "name"
        const val KEY_TYPE = "type"
        const val KEY_STATUS = "status"
        const val KEY_SHEDULED_START_TIME = "scheduled_start_time"

        const val KEY_TASK_ID = "task_id"
        const val KEY_SCRIPT_ID = "script_id"

        const val KEY_CHAT_ID = "chat_id"

        const val KEY_PHRASE = "phrase"
        const val KEY_GROUP_TYPE = "group_type"
        const val KEY_GROUP_COUNT_FROM = "group_count_from"
        const val KEY_GROUP_COUNT_TO = "group_count_to"
        const val KEY_AUTO_LIKING_POST = "auto_liking_post"
        const val KEY_FILED_POST = "filed_post"
        const val KEY_PRE_JOIN_GROUP = "pre_join_group"
        const val KEY_SETTING_TITLE = "setting_title"

        const val KEY_USER_AGENT = "user_agent"

        const val KEY_COOKIE_NAME = "cookie_name"
        const val KEY_COOKIE_VALUE = "cookie_value"

        const val KEY_POST_ID = "post_id"
        const val KEY_PLANNING_DATE_TIME = "planning_date_time"
        const val KEY_REPOST = "repost"
        const val KEY_SET_LIKE = "set_like"
        const val KEY_USER_NAME = "user_name"
        const val KEY_PLANNING_POST_MESSAGE = "planning_post_message"
        const val KEY_SET_COMMENT = "set_comment"
        const val KEY_COMMENT_PAUSE = "comment_pause"
        const val KEY_POST_ON_TELEGRAM = "post_on_telegram"
        const val KEY_TELEGRAM_CHATS = "telegram_chats"

        const val KEY_PLANNING_POST_ID = "planning_post_id"
        const val KEY_GROUP_NAME = "group_name"
        const val KEY_COMMENT_NAME = "comment_name"
        const val KEY_COMMENT_ID = "comment_id"

        const val KEY_OPERATION_TYPE = "operation_type"

        const val KEY_STATIC_DATE = "static_date"
        const val KEY_ACCOUNT_ID = "account_id"
        const val KEY_FR_REQUEST_COUNT = "fr_request_count"
        const val KEY_MESSAGES_COUNT = "messages_count"
        const val KEY_LIKES_COUNT = "likes_count"
        const val KEY_POSTS_COUNT = "posts_count"
        const val KEY_UNFOLLOW_COUNT = "unfollow_count"

        const val KEY_ACCOUNT_NUMBER = "account_number"
        const val KEY_CURRENT_REQUESTS_COUNT = "current_requests_count"
        const val KEY_LAST_DATE = "last_date"
        const val KEY_IS_FULL = "is_full"
        const val KEY_IS_PAYED = "is_payed"
        const val KEY_THREAD_NUMBER = "thread_number"

        const val KEY_TASK_TYPE = "task_type"
        const val KEY_HISTORY_TEXT = "history_text"
        const val KEY_HISTORY_TIME = "history_time"
        const val KEY_CURRENT_DATE = "current_d"

        const val KEY_IS_START_TASK_MANAGER = "is_start_task_manager"
        const val KEY_NOTIF_SOUND_ACTIVATED = "notif_sound_activated"
        const val KEY_NOTIF_VIBRATION_ACTIVATED = "notif_vibration_activated"

        const val KEY_CREATE_DATE = "create_date"
        const val KEY_MODIFIED_DATE = "modified_date"
        const val KEY_LAST_LOGGED_IN = "last_logged_in"

        const val KEY_IS_MEMBER = "is_member"

        fun getInstance(c: Context): DbHelper? {
            if (mInstance == null) {
                mInstance = DbHelper(c.applicationContext as Application) //fails here
            }
            return mInstance
        }
    }


}