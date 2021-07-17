package com.suret.task.data.api

import com.suret.task.data.list.GetList
import com.suret.task.data.model.ChatModel
import retrofit2.mock.BehaviorDelegate

class MockAPI(private val delegate: BehaviorDelegate<API>) : API {
    private val listOfUsers: ArrayList<ChatModel> = GetList.getList()

    override suspend fun getUsers(): List<ChatModel> {
        return delegate.returningResponse(listOfUsers).getUsers()
    }

    override suspend fun sendMessage(chatModel: ChatModel): List<ChatModel> {
        val iterator: MutableIterator<ChatModel> = listOfUsers.iterator()
        while (iterator.hasNext()) {
            val model = iterator.next()
            if (model.id == chatModel.id) {
                model.message = chatModel.message
            }
        }
        return listOfUsers
    }

}