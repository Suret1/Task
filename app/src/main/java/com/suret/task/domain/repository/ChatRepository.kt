package com.suret.task.domain.repository

import com.suret.task.data.model.ChatModel
import com.suret.task.data.model.MessageModel

interface ChatRepository {
    suspend fun getUsers(): List<ChatModel>
    suspend fun sendMessage(chatModel : ChatModel): List<ChatModel>
}