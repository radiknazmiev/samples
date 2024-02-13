package com.nazmiev.radik.vkclient.core.di

import com.nazmiev.radik.vkclient.core.db.models.GroupsForProcessing
import com.nazmiev.radik.vkclient.core.usecases.CaptchaUseCase
import com.nazmiev.radik.vkclient.core.usecases.CaptchaUseCaseImpl
import com.nazmiev.radik.vkclient.core.usecases.DateUseCase
import com.nazmiev.radik.vkclient.core.usecases.DateUseCaseImpl
import com.nazmiev.radik.vkclient.core.usecases.FriendUseCase
import com.nazmiev.radik.vkclient.core.usecases.FriendUseCaseImpl
import com.nazmiev.radik.vkclient.core.usecases.GroupSearchSettingUseCase
import com.nazmiev.radik.vkclient.core.usecases.GroupSearchSettingUseCaseImpl
import com.nazmiev.radik.vkclient.core.usecases.GroupsForProcessingUseCase
import com.nazmiev.radik.vkclient.core.usecases.GroupsForProcessingUseCaseImpl
import com.nazmiev.radik.vkclient.core.usecases.UserUseCase
import com.nazmiev.radik.vkclient.core.usecases.UserUseCaseImpl
import com.nazmiev.radik.vkclient.core.usecases.MessageUseCase
import com.nazmiev.radik.vkclient.core.usecases.MessageUseCaseImpl
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
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class UseCaseModule {

    @Binds
    abstract fun provideSharedPreferencesUseCase(sharedPreferencesUseCaseImpl: SharedPreferencesUseCaseImpl): SharedPreferencesUseCase

    @Binds
    abstract fun provideDateUseCase(dateUseCaseImpl: DateUseCaseImpl): DateUseCase

    @Binds
    abstract fun provideLocalUserUseCase(localUserUseCaseImpl: UserUseCaseImpl): UserUseCase

    @Binds
    abstract fun provideRemoteUserUseCase(remoteUserUseCaseImpl: RemoteUserUseCaseImpl): RemoteUserUseCase

    @Binds
    abstract fun provideFriendsUseCase(friendUseCaseImpl: FriendUseCaseImpl): FriendUseCase

    @Binds
    abstract fun provideProxyUseCase(proxyUseCaseImpl: ProxyUseCaseImpl): ProxyUseCase

    @Binds
    abstract fun provideTaskUseCase(taskUseCaseImpl: TaskUseCaseImpl): TaskUseCase

    @Binds
    abstract fun provideMessageUseCase(messageUseCaseImpl: MessageUseCaseImpl): MessageUseCase

    @Binds
    abstract fun provideCaptchaUseCase(captchaUseCaseImpl: CaptchaUseCaseImpl): CaptchaUseCase

    @Binds
    abstract fun provideGroupSearchUseCase(groupSearchSettingUseCaseImpl: GroupSearchSettingUseCaseImpl): GroupSearchSettingUseCase

    @Binds
    abstract fun provideGroupsForProcessing(groupsForProcessingUseCaseImpl: GroupsForProcessingUseCaseImpl): GroupsForProcessingUseCase
}