package com.suret.task.data.repository

import com.suret.task.data.model.ChatModel
import com.suret.task.data.repository.datasource.RemoteDataSource
import com.suret.task.domain.repository.ChatRepository

class ChatRepositoryImpl(private val remoteDataSource: RemoteDataSource) : ChatRepository {
    override suspend fun getUsers(): List<ChatModel> {
        return remoteDataSource.getUsers()
    }

    override suspend fun sendMessage(chatModel: ChatModel): List<ChatModel> {
        return remoteDataSource.sendMessage(chatModel)
    }

}