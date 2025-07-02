package com.example.unitedtractor.api.model

data class LoginResponse(
    val status: Int,
    val body: UserBody
)

data class UserBody(
    val userId: String,
    val userName: String,
    val firstName: String,
    val lastName: String,
    val email: String,
    val roleLevel: Int,
    val regionLevel: String,
    val tokenResponse: TokenResponse
)

data class TokenResponse(
    val accessToken: String,
    val tokenType: String,
    val refreshToken: String,
    val expiresIn: Int
)
