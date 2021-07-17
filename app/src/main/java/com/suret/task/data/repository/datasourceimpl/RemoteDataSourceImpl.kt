package com.suret.task.data.repository.datasourceimpl

import com.suret.task.data.api.API
import com.suret.task.data.model.ChatModel
import com.suret.task.data.repository.datasource.RemoteDataSource

class RemoteDataSourceImpl(private val api: API) : RemoteDataSource {
    override suspend fun getUsers(): List<ChatModel> {
        return api.getUsers()
    }

    override suspend fun sendMessage(chatModel: ChatModel): List<ChatModel> {
        return api.sendMessage(chatModel)
    }

}