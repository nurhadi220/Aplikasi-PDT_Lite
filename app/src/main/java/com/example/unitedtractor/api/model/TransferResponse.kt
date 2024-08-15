package com.example.unitedtractor.api.model

data class TransferResponse(
    val status: Int,
    val pesan: String,
    val data: Transfer?
)