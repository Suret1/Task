package com.suret.task.data.list

import com.suret.task.data.model.ChatModel
import kotlin.random.Random

class GetList {
    companion object {
        fun getList(): ArrayList<ChatModel> {
            val list: ArrayList<ChatModel> = arrayListOf()
            list.add(
                ChatModel(
                    Random.nextInt(),
                    "Frank Martin",
                    null,
                    "https://cutt.ly/nmLnXdZ",
                    false,
                    Random.nextBoolean()
                )
            )
            list.add(
                ChatModel(
                    Random.nextInt(),
                    "John",
                    null,
                    "https://cutt.ly/kmLnM1E",
                    false,
                    Random.nextBoolean()
                )
            )
            list.add(
                ChatModel(
                    Random.nextInt(),
                    "Martha Steward",
                    null,
                    "https://cutt.ly/amLn5d8",
                    false,
                    Random.nextBoolean()
                )
            )
            list.add(
                ChatModel(
                    Random.nextInt(),
                    "Margot",
                    null,
                    "https://cutt.ly/amLmesJ",
                    false,
                    Random.nextBoolean()
                )
            )
            list.add(
                ChatModel(
                    Random.nextInt(),
                    "Alexis",
                    null,
                    "https://cutt.ly/0mLmoSO",
                    false,
                    Random.nextBoolean()
                )
            )
            list.add(
                ChatModel(
                    Random.nextInt(),
                    "Brad",
                    null,
                    "https://cutt.ly/YmLmj2F",
                    false,
                    Random.nextBoolean()
                )
            )
            list.add(
                ChatModel(
                    Random.nextInt(),
                    "Angelina",
                    null,
                    "https://cutt.ly/hmLmmwa",
                    false,
                    Random.nextBoolean()
                )
            )
            return list
        }

    }

}