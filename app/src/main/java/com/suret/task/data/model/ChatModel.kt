package com.suret.task.data.model

import android.os.Parcelable
import androidx.annotation.Keep
import kotlinx.parcelize.Parcelize

@Keep
@Parcelize
data class ChatModel(
    val id: Int,
    val name: String,
    var message: String?,
    var profilePhoto: String?,
    var isForward: Boolean = false,
    val isOnline: Boolean = false,
) : Parcelable
