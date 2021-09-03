package com.example.sephoraapptestapplication.model

import com.google.gson.annotations.SerializedName

data class Included (
    @SerializedName("type")
    val type : String,
    @SerializedName("id")
    val id : Int,
    @SerializedName("attributes")
    val attributes : Attributes
    )