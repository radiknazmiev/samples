package com.nazmiev.radik.vkclient.core.usecases

interface SharedPreferencesUseCase {

    fun getPauseMSeconds(): Int

    fun getIsUseLatinReplace(): Boolean

    fun isUseMultiThreading(): Boolean

    fun setLogsSortType(sortType: String)

    fun getLogsSortType(): String

    companion object {
        const val PREF_FIELD_LOGS_GROUP_BY_THREADS = "logs_group_by_threads"
        const val PREF_FIELD_LOGS_SORT_BY_TIME = "logs_sort_by_time"
    }
}