package com.example.hackathon

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class QuizData(
    val quizTitle: String,
    val quizChoice1: String,
    val quizChoice2: String,
    val quizChoice3: String,
    val answer: Int,
    val explanation: String
): Parcelable
