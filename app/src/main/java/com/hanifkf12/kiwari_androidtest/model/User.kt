package com.hanifkf12.kiwari_androidtest.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class User(var username : String? = "", var name : String? = "", var email : String="", var password : String? = "", var avatar : String? = "") : Parcelable