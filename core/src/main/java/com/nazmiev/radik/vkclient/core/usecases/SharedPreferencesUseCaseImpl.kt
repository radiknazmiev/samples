package com.nazmiev.radik.vkclient.core.usecases

import android.content.SharedPreferences
import androidx.core.content.edit
import java.util.Random
import javax.inject.Inject

class SharedPreferencesUseCaseImpl @Inject constructor(private val sharedPreferences: SharedPreferences): SharedPreferencesUseCase {

    override fun getPauseMSeconds(): Int {
        val minPauseMSeconds = sharedPreferences.getInt("min_pause_seconds", 5000)
        val maxPauseMSeconds = sharedPreferences.getInt("max_pause_seconds", 20000)
        val rnd = Random(System.currentTimeMillis())
        return minPauseMSeconds + rnd.nextInt(maxPauseMSeconds - minPauseMSeconds + 1)
    }

    override fun getIsUseLatinReplace(): Boolean {
        return sharedPreferences.getBoolean("use_latin_replaces", true)
    }

    override fun isUseMultiThreading(): Boolean {
        return sharedPreferences.getBoolean("use_two_threads", false)
    }

    override fun setLogsSortType(sortType: String) {
        sharedPreferences.edit {
            putString("task_log_sort_type", sortType)
        }
    }

    override fun getLogsSortType(): String {
        return sharedPreferences.getString("task_log_sort_type", "")!!
    }


}