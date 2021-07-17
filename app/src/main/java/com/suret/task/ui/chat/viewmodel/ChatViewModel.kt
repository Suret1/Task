package com.suret.task.ui.chat.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.suret.task.data.model.ChatModel
import com.suret.task.domain.usecase.GetUsersUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ChatViewModel @Inject constructor(private val getUsersUseCase: GetUsersUseCase) :
    ViewModel() {
    private val _messages = MutableLiveData<List<ChatModel>>()
    val messages: LiveData<List<ChatModel>>
        get() = _messages

    init {
        getMessages()
    }

    private fun getMessages() {
        viewModelScope.launch {
            val result = getUsersUseCase.execute()
            _messages.postValue(result)
        }
    }
}