package com.nazmiev.radik.vkclient.core.di

import com.nazmiev.radik.vkclient.core.repositories.CaptchaRepository
import com.nazmiev.radik.vkclient.core.repositories.CaptchaRepositoryImpl
import com.nazmiev.radik.vkclient.core.repositories.FriendsRepository
import com.nazmiev.radik.vkclient.core.repositories.FriendsRepositoryImpl
import com.nazmiev.radik.vkclient.core.repositories.GroupsForProcessingRepository
import com.nazmiev.radik.vkclient.core.repositories.GroupsForProcessingRepositoryImpl
import com.nazmiev.radik.vkclient.core.repositories.MessageRepositoryImpl
import com.nazmiev.radik.vkclient.core.repositories.MessagesRepository
import com.nazmiev.radik.vkclient.core.repositories.ProxyRepository
import com.nazmiev.radik.vkclient.core.repositories.ProxyRepositoryImpl
import com.nazmiev.radik.vkclient.core.repositories.TaskRepository
import com.nazmiev.radik.vkclient.core.repositories.TaskRepositoryImpl
import com.nazmiev.radik.vkclient.core.repositories.UserRepository
import com.nazmiev.radik.vkclient.core.repositories.UserRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoriesModule {

    @Binds
    abstract fun provideTaskRepository(taskRepositoryImpl: TaskRepositoryImpl): TaskRepository

    @Binds
    abstract fun provideProxyRepository(proxyRepositoryImpl: ProxyRepositoryImpl): ProxyRepository

    @Binds
    abstract fun provideUserRepository(userRepositoryImpl: UserRepositoryImpl): UserRepository

    @Binds
    abstract fun provideFriendsRepository(friendsRepositoryImpl: FriendsRepositoryImpl): FriendsRepository

    @Binds
    abstract fun provideMessagesRepository(messageRepositoryImpl: MessageRepositoryImpl): MessagesRepository

    @Binds
    abstract fun provideCaptchaRepository(captchaRepositoryImpl: CaptchaRepositoryImpl): CaptchaRepository

    @Binds
    abstract fun provideGroupsForProcessing(groupsForProcessingRepositoryImpl: GroupsForProcessingRepositoryImpl): GroupsForProcessingRepository
}