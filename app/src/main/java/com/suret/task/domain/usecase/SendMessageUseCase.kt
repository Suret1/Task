package com.suret.task.domain.usecase

import com.suret.task.data.model.ChatModel
import com.suret.task.domain.repository.ChatRepository

class SendMessageUseCase(private val chatRepository: ChatRepository) {

    suspend fun execute(chatModel: ChatModel): List<ChatModel> {
        return chatRepository.sendMessage(chatModel)
    }

}