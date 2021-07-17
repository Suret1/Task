package com.suret.task.data.api

import com.suret.task.data.model.ChatModel
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface API {

    @GET("/messages")
    suspend fun getUsers(): List<ChatModel>

    @POST("/send")
    suspend fun sendMessage(@Body chatModel: ChatModel): List<ChatModel>


}