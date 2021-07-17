package com.suret.task.ui.chatdetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.suret.task.R
import com.suret.task.data.model.ChatModel
import com.suret.task.data.other.Constants.CHAT_MODEL
import com.suret.task.data.util.Utils
import com.suret.task.databinding.FragmentChatDetailsBinding
import com.suret.task.ui.chatdetails.adapter.MessageAdapter
import com.suret.task.ui.chatdetails.viewmodel.MessageViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class ChatDetailsFragment : Fragment() {
    private lateinit var chatDetailsBinding: FragmentChatDetailsBinding
    private var chatModel: ChatModel? = null
    private val messageViewModel: MessageViewModel by viewModels()
    private lateinit var messageAdapter: MessageAdapter
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        chatDetailsBinding = FragmentChatDetailsBinding.inflate(inflater, container, false)
        return chatDetailsBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        chatModel = arguments?.getParcelable(CHAT_MODEL)
        messageAdapter = MessageAdapter(requireContext())
        Utils.hideKeyboard(requireActivity())

        chatDetailsBinding.apply {
            chatDetailsToolbar.setNavigationOnClickListener {
                activity?.onBackPressed()
            }

            rvChat.adapter = messageAdapter

            setToolbarTitleAndSubtitle()
            sendMessage()

        }
    }

    private fun FragmentChatDetailsBinding.setToolbarTitleAndSubtitle() {
        chatModel?.let {
            chatDetailsToolbar.title = it.name
            if (chatModel?.isOnline == true) {
                chatDetailsToolbar.subtitle = getString(R.string.active_now)
            } else {
                chatDetailsToolbar.subtitle = getString(R.string.offline)
            }
        }
    }

    private fun FragmentChatDetailsBinding.sendMessage() {
        val list: MutableList<ChatModel> = arrayListOf()
        btnSend.setOnClickListener {
            if (!textInputEditTextMessage.text.toString().isNullOrEmpty()) {
                chatModel?.let {
                    val sendChatModel = ChatModel(
                        it.id,
                        it.name,
                        textInputEditTextMessage.text.toString(),
                        "https://cutt.ly/PmJ1nXj",
                        true,
                        it.isOnline
                    )
                    messageViewModel.sendMessage(sendChatModel)
                    it.isForward = false
                    list.add(sendChatModel)
                    list.add(it)
                    messageAdapter.submitList(list)
                    rvChat.adapter?.itemCount?.let { it1 -> rvChat.scrollToPosition(it1.minus(1)) }
                }
                textInputEditTextMessage.setText("")
            } else {
                Toast.makeText(
                    requireContext(),
                    "Message must not be empty",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }
}