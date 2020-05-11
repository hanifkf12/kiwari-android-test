package com.hanifkf12.kiwari_androidtest.util

import android.content.Context

class PreferenceHelper(private val context: Context){
    companion object{
        const val USERNAME = "USERNAME"
        const val LOGIN = "LOGIN"
    }
    private val sharedPreferences by lazy {
        context.getSharedPreferences("my-chat-app", Context.MODE_PRIVATE)
    }
    private val editor by lazy {
        sharedPreferences.edit()
    }

    var username : String?
        get() {
            return sharedPreferences.getString(USERNAME,null)
        }
        set(value) {
            editor.apply {
                putString(USERNAME,value)
                apply()
            }
        }

    var isLogin : Boolean
        get() {
            return sharedPreferences.getBoolean(LOGIN,false)
        }
        set(value) {
            editor.apply {
                putBoolean(LOGIN, value)
                apply()
            }
        }
}