package com.suret.task.ui.chat.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.CircleCropTransformation
import com.suret.task.data.model.ChatModel
import com.suret.task.data.model.StoryModel
import com.suret.task.databinding.StoryListLayoutBinding

class StoryAdapter : RecyclerView.Adapter<StoryAdapter.StoryViewHolder>() {

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

    inner class StoryViewHolder(private val storyListLayoutBinding: StoryListLayoutBinding) :
        RecyclerView.ViewHolder(storyListLayoutBinding.root) {
        fun bind(storyModel: ChatModel?) {
            if (storyModel != null) {
                storyListLayoutBinding.apply {
                    tvProfileName.text = storyModel.name
                    iwProfile.load(storyModel.profilePhoto){
                        crossfade(true)
                        transformations(CircleCropTransformation())
                    }
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StoryViewHolder {
        return StoryViewHolder(
            StoryListLayoutBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: StoryViewHolder, position: Int) {
        holder.bind(differ.currentList.getOrNull(position))
    }

    override fun getItemCount(): Int = differ.currentList.size

}