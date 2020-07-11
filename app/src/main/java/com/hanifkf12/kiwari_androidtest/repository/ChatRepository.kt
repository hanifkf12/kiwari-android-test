package com.hanifkf12.kiwari_androidtest.repository

import android.util.Log
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.Query
import com.hanifkf12.kiwari_androidtest.model.Chat
import com.hanifkf12.kiwari_androidtest.util.FirebaseUtil

class ChatRepository {
    companion object{
        val TAG = ChatRepository::class.java.simpleName
    }
    fun retrieveChats(onResult : (List<Chat>)->Unit) {
        val chatList =  mutableListOf<Chat>()
        FirebaseUtil.provideFireStore().collection("chatRooms").document("room01")
            .collection("messages").orderBy("timestamps", Query.Direction.DESCENDING)
            .addSnapshotListener { querySnapshot, e ->
                if (e != null) {
                    Log.w(TAG, "Listen failed.", e)
                    return@addSnapshotListener
                }
                chatList.clear()
                for (doc in querySnapshot!!){
                    val chat = doc.toObject(Chat::class.java)
                    chatList.add(chat)
                }
                onResult(chatList)
            }
    }

    fun storeChat(chat: Chat){
        val chatMap = hashMapOf("username" to chat.username!! , "content" to chat.content!!, "timestamps" to FieldValue.serverTimestamp())
        FirebaseUtil.provideFireStore().collection("chatRooms").document("room01")
            .collection("messages")
            .add(chatMap)
    }
}