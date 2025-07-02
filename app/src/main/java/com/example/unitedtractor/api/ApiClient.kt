package com.example.unitedtractor.api

import com.example.unitedtractor.api.model.TransferOrderResponse
import com.example.unitedtractor.api.model.LoginResponse
import com.example.unitedtractor.api.services.TransferOrder
import com.example.unitedtractor.api.services.AuthService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {

    private const val BASE_URL = "http://192.168.54.176:5000/warehouse-api/api/v1/"
    private const val BASE_URL_LOGIN = "https://dev-aks.unitedtractors.com:30203/synapse/ums/"

    // Retrofit instance untuk TransferOrder
    private val retrofitTransferOrder: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    // Retrofit instance untuk AuthService (Login)
    private val retrofitAuthService: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL_LOGIN)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val transferServices: TransferOrder by lazy {
        retrofitTransferOrder.create(TransferOrder::class.java)
    }

    val authService: AuthService by lazy {
        retrofitAuthService.create(AuthService::class.java)
    }

}
