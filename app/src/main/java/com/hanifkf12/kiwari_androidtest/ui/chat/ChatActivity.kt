package com.hanifkf12.kiwari_androidtest.ui.chat

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.hanifkf12.kiwari_androidtest.R
import com.hanifkf12.kiwari_androidtest.adapter.ChatAdapter
import com.hanifkf12.kiwari_androidtest.model.Chat
import com.hanifkf12.kiwari_androidtest.model.User
import com.hanifkf12.kiwari_androidtest.repository.ChatRepository
import com.hanifkf12.kiwari_androidtest.util.PreferenceHelper
import kotlinx.android.synthetic.main.activity_chat.*

class ChatActivity : AppCompatActivity() {
    companion object{
        const val USER = "USER"
    }
    private lateinit var viewModel: ChatViewModel
    private lateinit var adapter : ChatAdapter
    private val listChat : MutableList<Chat> = mutableListOf()
    private lateinit var preferenceHelper: PreferenceHelper
    private lateinit var repository: ChatRepository
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat)
        viewModel = ViewModelProvider(this).get(ChatViewModel::class.java)
        val intent = intent
        val otherUser = intent.getParcelableExtra<User>(USER)
        Glide.with(this).load(otherUser?.avatar).into(iv_other_users)
        tv_other_users.text = otherUser?.name
        repository = ChatRepository()
        preferenceHelper = PreferenceHelper(this)
        adapter = ChatAdapter(listChat,preferenceHelper.username!!)
        adapter.notifyDataSetChanged()
        val layoutManager = LinearLayoutManager(this)
        layoutManager.reverseLayout = true
        rv_chat.layoutManager = layoutManager
        rv_chat.setHasFixedSize(true)
        rv_chat.adapter = adapter
        viewModel.retrieveChats()
        viewModel.chats.observe(this, Observer {
            it?.let{
                if (it.isEmpty()){
                    showNoChats()
                }else{
                    hideNoChats()
                    listChat.clear()
                    listChat.addAll(it)
                    adapter.notifyDataSetChanged()
                    Log.d("LIST CHAT", it.size.toString())
                }

            }

        })


        btn_send.setOnClickListener {
            val content = et_message.text.toString()
            if (content.isNotEmpty()){
                val chat = Chat(preferenceHelper.username,content, null)
                viewModel.storeChat(chat)
                et_message.setText("")
            }else{
                Toast.makeText(this,"Message Can't be Empty", Toast.LENGTH_SHORT).show()
            }

        }
        btn_back.setOnClickListener {
            onBackPressed()
        }
    }

    private fun showNoChats(){
        rv_chat.visibility = View.GONE
        iv_no_chat.visibility = View.VISIBLE
    }

    private fun hideNoChats(){
        rv_chat.visibility = View.VISIBLE
        iv_no_chat.visibility = View.GONE
    }
}
