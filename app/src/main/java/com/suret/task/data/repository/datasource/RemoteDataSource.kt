package com.suret.task.data.repository.datasource

import com.suret.task.data.model.ChatModel
import com.suret.task.data.model.MessageModel

interface RemoteDataSource {
    suspend fun getUsers(): List<ChatModel>
    suspend fun sendMessage(chatModel : ChatModel): List<ChatModel>
}