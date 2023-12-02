package com.nazmiev.radik.vkclient.core.di

import android.content.SharedPreferences
import com.nazmiev.radik.vkclient.core.usecases.DateUseCase
import com.nazmiev.radik.vkclient.core.usecases.DateUseCaseImpl
import com.nazmiev.radik.vkclient.core.usecases.FriendUseCase
import com.nazmiev.radik.vkclient.core.usecases.FriendUseCaseImpl
import com.nazmiev.radik.vkclient.core.usecases.LocalUserUseCase
import com.nazmiev.radik.vkclient.core.usecases.LocalUserUseCaseImpl
import com.nazmiev.radik.vkclient.core.usecases.ProxyUseCase
import com.nazmiev.radik.vkclient.core.usecases.ProxyUseCaseImpl
import com.nazmiev.radik.vkclient.core.usecases.RemoteUserUseCase
import com.nazmiev.radik.vkclient.core.usecases.RemoteUserUseCaseImpl
import com.nazmiev.radik.vkclient.core.usecases.SharedPreferencesUseCase
import com.nazmiev.radik.vkclient.core.usecases.SharedPreferencesUseCaseImpl
import com.nazmiev.radik.vkclient.core.usecases.TaskUseCase
import com.nazmiev.radik.vkclient.core.usecases.TaskUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class UseCaseModule {

    @Binds
    abstract fun provideSharedPreferencesUseCase(sharedPreferencesUseCaseImpl: SharedPreferencesUseCaseImpl): SharedPreferencesUseCase

    @Binds
    abstract fun provideDateUseCase(dateUseCaseImpl: DateUseCaseImpl): DateUseCase

    @Binds
    abstract fun provideLocalUserUseCase(localUserUseCaseImpl: LocalUserUseCaseImpl): LocalUserUseCase

    @Binds
    abstract fun provideRemoteUserUseCase(remoteUserUseCaseImpl: RemoteUserUseCaseImpl): RemoteUserUseCase

    @Binds
    abstract fun provideFriendsUseCase(friendUseCaseImpl: FriendUseCaseImpl): FriendUseCase

    @Binds
    abstract fun provideProxyUseCase(proxyUseCaseImpl: ProxyUseCaseImpl): ProxyUseCase

    @Binds
    abstract fun provideTaskUseCase(taskUseCaseImpl: TaskUseCaseImpl): TaskUseCase
}