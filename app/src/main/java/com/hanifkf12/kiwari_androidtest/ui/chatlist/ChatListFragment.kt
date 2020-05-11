package com.hanifkf12.kiwari_androidtest.ui.chatlist

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.hanifkf12.kiwari_androidtest.R
import com.hanifkf12.kiwari_androidtest.adapter.ChatListAdapter
import com.hanifkf12.kiwari_androidtest.model.User
import com.hanifkf12.kiwari_androidtest.ui.chat.ChatActivity
import com.hanifkf12.kiwari_androidtest.util.PreferenceHelper
import kotlinx.android.synthetic.main.fragment_chat_list.*

class ChatListFragment : Fragment() {
    companion object{
        const val USER = "USER"
    }
    private lateinit var chatListViewModel: ChatListViewModel
    private lateinit var adapter : ChatListAdapter
    private val userList : MutableList<User> = mutableListOf()
    private lateinit var preferenceHelper: PreferenceHelper
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_chat_list, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        chatListViewModel = ViewModelProvider(requireActivity()).get(ChatListViewModel::class.java)
        adapter = ChatListAdapter(userList)
        adapter.setOnItemClickListener {
            navigateToChat(it)
        }
        preferenceHelper = PreferenceHelper(requireActivity())
        rv_chat_list.adapter = adapter
        rv_chat_list.setHasFixedSize(true)
        rv_chat_list.layoutManager = LinearLayoutManager(requireActivity())
        chatListViewModel.showChatList(preferenceHelper.username!!)
        chatListViewModel.user.observe(viewLifecycleOwner, Observer {
            if (it.isNotEmpty()){
                userList.clear()
                userList.addAll(it)
                adapter.notifyDataSetChanged()
            }
        })

    }

    private fun navigateToChat(otherUser : User){
        val intent = Intent(requireActivity(), ChatActivity::class.java)
        intent.putExtra(USER, otherUser)
        startActivity(intent)
    }
}
