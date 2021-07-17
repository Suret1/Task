package com.suret.task.data.model

import android.os.Parcelable
import androidx.annotation.Keep
import kotlinx.parcelize.Parcelize

@Keep
@Parcelize
data class MessageModel(
    val id: Int,
    val name: String?,
    val message: String?,
    val profilePhoto: String?,
    val isForward: Boolean = false,
    val isOnline: Boolean = false
) : Parcelable