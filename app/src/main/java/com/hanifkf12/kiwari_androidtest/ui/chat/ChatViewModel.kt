package com.hanifkf12.kiwari_androidtest.ui.chat

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.hanifkf12.kiwari_androidtest.model.Chat
import com.hanifkf12.kiwari_androidtest.repository.ChatRepository

class ChatViewModel : ViewModel(){
    private val repository = ChatRepository()
    private val _chats : MutableLiveData<List<Chat>?> = MutableLiveData()
    val chats : LiveData<List<Chat>?> = _chats

    fun retrieveChats(){
        repository.retrieveChats {
            _chats.value = it
        }
    }
    fun storeChat(chat: Chat){
        repository.storeChat(chat)
    }
}