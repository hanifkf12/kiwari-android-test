package com.hanifkf12.kiwari_androidtest.ui.chatlist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.hanifkf12.kiwari_androidtest.model.User
import com.hanifkf12.kiwari_androidtest.repository.UserRepository

class ChatListViewModel : ViewModel() {

    private val _user : MutableLiveData<List<User>> = MutableLiveData<List<User>>()
    val user : LiveData<List<User>> = _user
    private var repository: UserRepository = UserRepository()

    fun showChatList(username : String){
        repository.listChatUser(username){
            _user.value = it
        }
    }
}