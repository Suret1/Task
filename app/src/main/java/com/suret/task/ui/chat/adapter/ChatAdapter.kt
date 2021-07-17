package com.suret.task.ui.chat.adapter

import android.content.Context
import android.graphics.drawable.Drawable
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.CircleCropTransformation
import com.suret.task.R
import com.suret.task.data.model.ChatModel
import com.suret.task.databinding.MessageListLayoutBinding
import java.util.*
import kotlin.collections.ArrayList


class ChatAdapter(private val context:Context) : RecyclerView.Adapter<ChatAdapter.ChatViewHolder>(), Filterable {
    var chatList: ArrayList<ChatModel> = ArrayList()
    var chatListFiltered: ArrayList<ChatModel> = ArrayList()
    private val differCallBack = object : DiffUtil.ItemCallback<ChatModel>() {
        override fun areItemsTheSame(
            oldItem: ChatModel,
            newItem: ChatModel
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: ChatModel,
            newItem: ChatModel
        ): Boolean {
            return oldItem == newItem
        }

    }
    val differ = AsyncListDiffer(this, differCallBack)

    inner class ChatViewHolder(private val messageListLayoutBinding: MessageListLayoutBinding) :
        RecyclerView.ViewHolder(messageListLayoutBinding.root) {
        fun bind(chatModel: ChatModel?) {
            if (chatModel != null) {
                messageListLayoutBinding.apply {
                    iwProfile.load(chatModel.profilePhoto) {
                        crossfade(true)
                        transformations(CircleCropTransformation())
                    }
                    tvProfileName.text = chatModel.name
                    tvMessages.text = chatModel.message
                    if(chatModel.isOnline){
                        iwCheck.background = ContextCompat.getDrawable(context,R.drawable.circular_active_badge)
                    }else{
                        iwCheck.background = ContextCompat.getDrawable(context,R.drawable.circular_offline_badge)
                    }
                }
            }
            messageListLayoutBinding.root.setOnClickListener {
                chatModel?.let { messages ->
                    setOnItemClickListener?.invoke(messages)
                }
            }
        }
    }

    fun addData(list: ArrayList<ChatModel>) {
        chatList = list
        chatListFiltered = chatList
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChatViewHolder {
        return ChatViewHolder(
            MessageListLayoutBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    private var setOnItemClickListener: ((ChatModel) -> Unit)? = null

    fun setOnItemClickListener(listener: (ChatModel) -> Unit) {
        setOnItemClickListener = listener
    }

    override fun onBindViewHolder(holder: ChatViewHolder, position: Int) {
        holder.bind(differ.currentList.getOrNull(position))
    }

    override fun getItemCount(): Int = differ.currentList.size

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val query = constraint.toString()
                if (query.isEmpty()) {
                    chatListFiltered = chatList
                }
                    val filteredList: ArrayList<ChatModel> = arrayListOf()
                    for (i in chatList) {
                        if (i.name.lowercase(Locale.ROOT).contains(query.lowercase())) {
                            filteredList.add(i)
                        }
                    }
                    chatListFiltered = filteredList
                    val filterResults = FilterResults()
                    filterResults.values = chatListFiltered
                    return filterResults

            }

            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                chatListFiltered = results?.values as ArrayList<ChatModel>
                if(!chatListFiltered.isNullOrEmpty()){
                    differ.submitList(chatListFiltered)
                }
            }
        }
    }


}