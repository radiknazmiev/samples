package com.nazmiev.radik.vkclient.core.di

import com.nazmiev.radik.vkclient.core.db.AppDatabase
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@EntryPoint
interface DbEntryPoint {

    fun getDb(): AppDatabase
}