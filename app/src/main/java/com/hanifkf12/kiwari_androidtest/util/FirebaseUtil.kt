package com.hanifkf12.kiwari_androidtest.util

import com.google.firebase.firestore.FirebaseFirestore

object FirebaseUtil {
    fun provideFireStore() : FirebaseFirestore{
        return FirebaseFirestore.getInstance()
    }
}