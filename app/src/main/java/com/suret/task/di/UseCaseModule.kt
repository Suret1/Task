package com.suret.task.di

import com.suret.task.domain.repository.ChatRepository
import com.suret.task.domain.usecase.GetUsersUseCase
import com.suret.task.domain.usecase.SendMessageUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {

    @Singleton
    @Provides
    fun provideGetUsersUseCase(chatRepository: ChatRepository): GetUsersUseCase {
        return GetUsersUseCase(chatRepository)
    }

    @Singleton
    @Provides
    fun provideSendMessageUseCase(chatRepository: ChatRepository): SendMessageUseCase {
        return SendMessageUseCase(chatRepository)
    }

}