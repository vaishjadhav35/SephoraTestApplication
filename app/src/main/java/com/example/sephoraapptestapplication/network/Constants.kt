package com.example.sephoraapptestapplication.network

interface Constants {
    interface ApiMethod {
        companion object {
            val GET_METHOD = 0
        }
    }

    companion object {
        val BASEURL = "https://api.sephora.sg/api/"
        val REPOSITORIES_URL = "v2.5/products?page[number]=1page[size]=30&include=featured_variant"
        val REPOSITORIES_SERVICE_ID = "REPOSITORIES_SERVICE_ID"
    }
}