package com.hanifkf12.kiwari_androidtest.dummy

import com.hanifkf12.kiwari_androidtest.model.User

object DummyUser {

    fun getUsers() : List<User>{
        return mutableListOf(
            User("jarjit","Jarjit Singh", "jarjit@mail.com","123456","https://api.adorable.io/avatars/160/jarjit@mail.com.png"),
            User("mail","Ismail bin Mail", "ismail@mail.com","123456","https://api.adorable.io/avatars/160/ismail@mail.com.png")
        )
    }
}