package com.suret.task.ui.chatdetails.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.CircleCropTransformation
import com.suret.task.R
import com.suret.task.data.model.ChatModel

class MessageAdapter(private val context: Context) :
    ListAdapter<ChatModel, RecyclerView.ViewHolder>(diffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        return when (viewType) {
            TYPE_MESSAGE_SENT -> {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_message_sent, parent, false)
                MessageSentViewHolder(view)
            }
            TYPE_MESSAGE_RECEIVED -> {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_message_received, parent, false)
                MessageReceivedViewHolder(view)
            }
            else -> {
                throw IllegalStateException("Cannot create view holder, view type is unknown $viewType")
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder.itemViewType) {
            TYPE_MESSAGE_SENT -> (holder as MessageSentViewHolder).bind(currentList[position])
            TYPE_MESSAGE_RECEIVED -> (holder as MessageReceivedViewHolder).bind(currentList[position])
        }
    }

    override fun getItemViewType(position: Int): Int {
        if (currentList[position].isForward) {
            return TYPE_MESSAGE_SENT
        }
        return TYPE_MESSAGE_RECEIVED
    }

    inner class MessageSentViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val textViewMessage: AppCompatTextView =
            itemView.findViewById(R.id.textView_message)
        private val iwProfilePhoto: AppCompatImageView = itemView.findViewById(R.id.iw_profile)
        private val iwCheck: View = itemView.findViewById(R.id.iwCheck)
        fun bind(message: ChatModel) {
            iwProfilePhoto.load(message.profilePhoto) {
                crossfade(true)
                transformations(CircleCropTransformation())
            }
            iwCheck.background =
                ContextCompat.getDrawable(context, R.drawable.circular_active_badge)
            textViewMessage.text = message.message
        }
    }

    inner class MessageReceivedViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val textViewMessage: AppCompatTextView =
            itemView.findViewById(R.id.textView_message)
        private val iwProfilePhoto: AppCompatImageView = itemView.findViewById(R.id.iw_profile)
        private val iwCheck: View = itemView.findViewById(R.id.iwCheck)

        fun bind(message: ChatModel) {
            iwProfilePhoto.load(message.profilePhoto) {
                crossfade(true)
                transformations(CircleCropTransformation())
            }
            if (message.isOnline) {
                iwCheck.background =
                    ContextCompat.getDrawable(context, R.drawable.circular_active_badge)
            } else {
                iwCheck.background =
                    ContextCompat.getDrawable(context, R.drawable.circular_offline_badge)
            }
            textViewMessage.text = message.message
        }
    }

    companion object {
        const val TYPE_MESSAGE_SENT = 0
        const val TYPE_MESSAGE_RECEIVED = 1

        val diffCallback = object : DiffUtil.ItemCallback<ChatModel>() {
            override fun areContentsTheSame(oldItem: ChatModel, newItem: ChatModel): Boolean {
                return oldItem == newItem
            }

            override fun areItemsTheSame(oldItem: ChatModel, newItem: ChatModel): Boolean {
                return oldItem.id == newItem.id
            }
        }
    }
}