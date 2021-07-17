package com.suret.task.ui.chat

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.suret.task.R
import com.suret.task.data.model.ChatModel
import com.suret.task.data.other.Constants.CHAT_MODEL
import com.suret.task.data.util.Utils
import com.suret.task.databinding.FragmentChatBinding
import com.suret.task.ui.chat.adapter.ChatAdapter
import com.suret.task.ui.chat.adapter.StoryAdapter
import com.suret.task.ui.chat.viewmodel.ChatViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ChatFragment : Fragment() {
    private lateinit var fragmentChatBinding: FragmentChatBinding
    private lateinit var storyAdapter: StoryAdapter
    private lateinit var chatAdapter: ChatAdapter
    private val chatViewModel: ChatViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        fragmentChatBinding = FragmentChatBinding.inflate(inflater, container, false)
        return fragmentChatBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        storyAdapter = StoryAdapter()
        chatAdapter = ChatAdapter(requireContext())

        Utils.hideKeyboard(requireActivity())

        fragmentChatBinding.apply {
            rvStory.adapter = storyAdapter
            rvChat.adapter = chatAdapter
            chatViewModel.messages.observe(viewLifecycleOwner) {
                chatAdapter.differ.submitList(it)
                storyAdapter.differ.submitList(it.reversed())
                chatAdapter.addData(it as ArrayList<ChatModel>)
            }
            chatAdapter.setOnItemClickListener {
                findNavController().navigate(R.id.action_to_chatDetailsFragment, bundleOf().apply {
                    putParcelable(CHAT_MODEL, it)
                })
            }

            searchView.setOnQueryTextListener(object :
                androidx.appcompat.widget.SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {
                    return false
                }

                override fun onQueryTextChange(newText: String?): Boolean {
                    chatAdapter.filter.filter(newText)
                    return true
                }

            })
        }
    }

}

