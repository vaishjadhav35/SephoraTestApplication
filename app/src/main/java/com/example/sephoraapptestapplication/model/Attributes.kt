package com.example.sephoraapptestapplication.model

import com.google.gson.annotations.SerializedName

data class Attributes (
    @SerializedName("product-name")
    val product_name : String,
    @SerializedName("original-price")
    val original_price : String,
    @SerializedName("image-urls")
    val image_urls : List<String>,
    @SerializedName("rating")
    val rating : String,
    @SerializedName("brand-name")
    val brand_name : String
    )