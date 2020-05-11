package com.hanifkf12.kiwari_androidtest.repository

import com.hanifkf12.kiwari_androidtest.dummy.DummyUser
import com.hanifkf12.kiwari_androidtest.model.Login
import com.hanifkf12.kiwari_androidtest.model.User

class UserRepository {
    fun loginUer(dataLogin: Login, onResult: (User?) -> Unit, onStatus: (String) -> Unit) {
        val data = DummyUser.getUsers().filter {
            it.email.contains(dataLogin.email)
        }.toList()
        if (data.isEmpty()) {
            onResult(null)
            onStatus("Email Not Found")
        } else {
            val user: User? = data[0]
            user?.let {
                if (it.password.equals(dataLogin.password)) {
                    onResult(it)
                    onStatus("Login Success")
                } else {
                    onStatus("Incorrect Password")
                    onResult(null)
                }
            }
        }

    }

    fun listChatUser(username : String, onResult: (List<User>?) -> Unit){
        val data = DummyUser.getUsers().filter {
            !it.username!!.contains(username)
        }.toList()
        onResult(data)
    }

    fun myProfile(username: String?, onResult: (User?) -> Unit){
        val data = DummyUser.getUsers().filter {
            it.username!!.contains(username!!)
        }.toList()
        onResult(data[0])
    }
}