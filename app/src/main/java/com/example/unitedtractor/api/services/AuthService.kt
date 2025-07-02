package com.example.unitedtractor.api.services

import com.example.unitedtractor.api.model.LoginRequest
import com.example.unitedtractor.api.model.LoginResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthService {
    @POST("login")
    fun login(@Body loginRequest: LoginRequest): Call<LoginResponse>
}