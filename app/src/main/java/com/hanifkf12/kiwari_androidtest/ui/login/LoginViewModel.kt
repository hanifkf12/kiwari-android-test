package com.hanifkf12.kiwari_androidtest.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.hanifkf12.kiwari_androidtest.model.Login
import com.hanifkf12.kiwari_androidtest.model.User
import com.hanifkf12.kiwari_androidtest.repository.UserRepository

class LoginViewModel  : ViewModel(){
    private val _user : MutableLiveData<User> = MutableLiveData<User>()
    val user : LiveData<User> = _user
    private val _status : MutableLiveData<String> = MutableLiveData<String>()
    val status : MutableLiveData<String> = _status
    private var repository: UserRepository = UserRepository()
    fun loginUser(dataLogin : Login){
        repository.loginUer(dataLogin,{
            _user.value = it
        },{
            _status.value = it
        })
    }
}