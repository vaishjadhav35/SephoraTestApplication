package com.example.sephoraapptestapplication.model

import com.google.gson.annotations.SerializedName

data class FetchRepositoryResponse(
    @SerializedName("included")
    val included: ArrayList<Included>
)

data class FetchRepositoryApiResponse(
    var isSuccess: Boolean,
    var message: String,
    var error: Int,
    var serviceID: String,
    var result: Any? = null
)