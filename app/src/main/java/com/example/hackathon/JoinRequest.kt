package com.example.hackathon

data class JoinRequest(
    val nickname: String,
    val password: String,
    val passwordCheck: String,
    val email: String
)
