package com.suret.task.ui.chatdetails.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.suret.task.data.model.ChatModel
import com.suret.task.domain.usecase.SendMessageUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MessageViewModel @Inject constructor(
    private val sendMessageUseCase: SendMessageUseCase
) : ViewModel() {

    fun sendMessage(chatModel: ChatModel): List<ChatModel> {
        var list: List<ChatModel> = arrayListOf()
        viewModelScope.launch {
            list = sendMessageUseCase.execute(chatModel)
        }
        return list
    }

}