package com.hanifkf12.kiwari_androidtest.ui.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.hanifkf12.kiwari_androidtest.model.User
import com.hanifkf12.kiwari_androidtest.repository.UserRepository

class ProfileViewModel : ViewModel() {
    private var repository: UserRepository = UserRepository()
    private val _user : MutableLiveData<User> = MutableLiveData<User>()
    val user : LiveData<User> = _user

    fun showProfile(username : String?){
        repository.myProfile(username) {
            _user.value = it
        }
    }
}