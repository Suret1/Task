package com.suret.task.domain.usecase

import com.suret.task.data.model.ChatModel
import com.suret.task.domain.repository.ChatRepository

class GetUsersUseCase(private val chatRepository: ChatRepository) {

    suspend fun execute() : List<ChatModel>{
        return chatRepository.getUsers()
    }

}