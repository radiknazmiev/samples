package com.nazmiev.radik.vkclient.core.usecases

import android.content.SharedPreferences
import java.util.Random
import javax.inject.Inject

class SharedPreferencesUseCaseImpl @Inject constructor(private val sharedPreferences: SharedPreferences): SharedPreferencesUseCase {

    override fun getPauseMSeconds(): Int {
        val minPauseMSeconds = sharedPreferences.getInt("min_pause_seconds", 5000)
        val maxPauseMSeconds = sharedPreferences.getInt("max_pause_seconds", 20000)
        val rnd = Random(System.currentTimeMillis())
        return minPauseMSeconds + rnd.nextInt(maxPauseMSeconds - minPauseMSeconds + 1)
    }
}