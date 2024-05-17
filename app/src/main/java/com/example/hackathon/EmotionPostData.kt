package com.example.hackathon

data class EmotionPostData(
    val exp: Int,
    val level: Int,
    val correct: Boolean,
    val explanation: String
)
