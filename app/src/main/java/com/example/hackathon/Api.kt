package com.example.hackathon

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface Api {
    @POST("members/login")
    fun login(
        @Body body: Map<String, String>
    ): Call<LoginData>

    @POST("members/new")
    fun join(
        @Body body: JoinRequest
    ): Call<JoinData>

    @POST("/chat")
    fun chat(
        @Body body: JoinData
    ): Call<ChattingData>

    @GET("/quiz")
    fun getQuiz(

    ): Call<QuizData>

    @GET("/generateEmotion")
    fun getEmotion(

    ): Call<EmotionRequestData>

    @POST("/checkEmotion")
    fun postEmotion(
        @Body body: String
    ): Call<EmotionPostData>

    @POST("/quiz/reward")
    fun updateExp(
        @Body body: UpdateData
    ): Call<LoginData>
}