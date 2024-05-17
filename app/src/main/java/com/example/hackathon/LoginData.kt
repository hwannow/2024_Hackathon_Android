package com.example.hackathon

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class LoginData(
    val nickname: String,
    val level: Int,
    val exp: Int
): Parcelable
