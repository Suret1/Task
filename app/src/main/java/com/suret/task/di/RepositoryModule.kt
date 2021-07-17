package com.suret.task.di

import com.suret.task.data.repository.ChatRepositoryImpl
import com.suret.task.data.repository.datasource.RemoteDataSource
import com.suret.task.domain.repository.ChatRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun provideChatRepository(
        remoteDataSource: RemoteDataSource
    ): ChatRepository {
        return ChatRepositoryImpl(remoteDataSource)
    }
}