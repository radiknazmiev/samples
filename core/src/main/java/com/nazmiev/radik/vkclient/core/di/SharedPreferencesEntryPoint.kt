package com.nazmiev.radik.vkclient.core.di

import android.content.SharedPreferences
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@EntryPoint
interface SharedPreferencesEntryPoint {

    fun getSharedPreferences() : SharedPreferences
}