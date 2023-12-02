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
//            database.execSQL("CREATE TABLE if not exists task_history_tmp (task_type integer,history_text text,history_time text,current_d text,user_id integer,thread_number integer);")
//            database.execSQL("INSERT INTO task_history_tmp(task_type, history_text, history_time, current_d, user_id, thread_number)\n" +
//                    "SELECT th.task_type, th.history_text, th.history_time, th.current_d, th.user_id, th.thread_number FROM task_history th")
//            database.execSQL("drop table task_history")
//            database.execSQL("CREATE TABLE if not exists task_history (`id` INTEGER NOT NULL,task_type integer NOT NULL,history_text text NOT NULL,history_time text NOT NULL,current_d text NOT NULL,user_id integer NOT NULL,thread_number integer NOT NULL, PRIMARY KEY(`id`))")
//            database.execSQL("INSERT INTO task_history(task_type, history_text, history_time, current_d, user_id, thread_number)\n" +
//                    "SELECT th.task_type, th.history_text, th.history_time, th.current_d, th.user_id, th.thread_number FROM task_history_tmp th")
//            database.execSQL("drop table task_history_tmp")
        }
    }

    private val MIGRATION_55_56 = object : Migration(58, 59) {
        override fun migrate(database: SupportSQLiteDatabase) {
            database.execSQL("CREATE TABLE if not exists users_tmp (_id integer primary key autoincrement,acc_tocken text,last_search text,is_def INTEGER,login text,requests_count INTEGER,incomplite_search text,skip_processing INTEGER,acc_title text,is_partner_acc INTEGER,sort_index INTEGER,last_liking text default '01.01.1900 00:00:00',last_message text default '01.01.1900 00:00:00',account_login text,account_password text,updated_at text);")
            database.execSQL("INSERT INTO users_tmp(_id, acc_tocken, last_search, is_def, login, requests_count, incomplite_search, skip_processing, acc_title, is_partner_acc, sort_index, last_liking, last_message, account_login, account_password, updated_at)\n" +
                    "SELECT th._id, th.acc_tocken, th.last_search, th.is_def, th.login, th.requests_count, th.incomplite_search, th.skip_processing, th.acc_title, th.is_partner_acc, th.sort_index, th.last_liking, th.last_message, th.account_login, th.account_password, th.updated_at FROM users th")
            database.execSQL("drop table users")
            database.execSQL("CREATE TABLE if not exists users (`_id` INTEGER NOT NULL, acc_tocken text,last_search text,is_def INTEGER,login text,requests_count INTEGER,incomplite_search text,skip_processing INTEGER,acc_title text,is_partner_acc INTEGER,sort_index INTEGER,last_liking text default '01.01.1900 00:00:00',last_message text default '01.01.1900 00:00:00',account_login text,account_password text,updated_at text, PRIMARY KEY(`_id`))")
            database.execSQL("INSERT INTO users(_id, acc_tocken, last_search, is_def, login, requests_count, incomplite_search, skip_processing, acc_title, is_partner_acc, sort_index, last_liking, last_message, account_login, account_password, updated_at)\n" +
                    "SELECT th._id, th.acc_tocken, th.last_search, th.is_def, th.login, th.requests_count, th.incomplite_search, th.skip_processing, th.acc_title, th.is_partner_acc, th.sort_index, th.last_liking, th.last_message, th.account_login, th.account_password, th.updated_at FROM users_tmp th")
            database.execSQL("drop table users_tmp")
        }

    }

    private val MIGRATION_60_61 = object : Migration(60, 61) {
        override fun migrate(database: SupportSQLiteDatabase) {
            database.execSQL("CREATE TABLE selected_accounts_for_task_tmp (task_id integer,accaunt integer);")
            database.execSQL("INSERT INTO selected_accounts_for_task_tmp(task_id, accaunt) select th.task_id, th.accaunt from selected_accounts_for_task th")
            database.execSQL("drop table selected_accounts_for_task")
            database.execSQL("CREATE TABLE if not exists selected_accounts_for_task (`id` INTEGER NOT NULL, task_id integer NOT NULL, accaunt integer NOT NULL, PRIMARY KEY(`id`))")
            database.execSQL("INSERT INTO selected_accounts_for_task(task_id, accaunt) select th.task_id, th.accaunt from selected_accounts_for_task_tmp th")
            database.execSQL("drop table selected_accounts_for_task_tmp")
        }

    }

    @Provides
    @Singleton
    fun provideDb(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java, "usersDB"
        )
            .fallbackToDestructiveMigration()
//            .addMigrations(MIGRATION_53_54, MIGRATION_55_56, MIGRATION_60_61)
            .build()
    }

    @Provides
    @Singleton
    fun provideOpenHelper(db: AppDatabase): SupportSQLiteOpenHelper {
        return db.openHelper
    }
}