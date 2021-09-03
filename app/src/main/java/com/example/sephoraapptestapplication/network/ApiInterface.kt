package com.example.sephoraapptestapplication.network

import com.example.sephoraapptestapplication.network.Constants.ApiMethod.Companion.GET_METHOD
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Url

interface ApiInterface {
    @GET
    fun getMethod(
        @Url methodUrl: String
    ): Observable<Any>

    companion object {
        fun callBack(
            apiCallInterface: ApiInterface?,
            apiMethod: Int,
            url: String
        ): Observable<Any>? {
            var call: Observable<Any>? = null
            when (apiMethod) {
                GET_METHOD -> call = apiCallInterface?.getMethod(url)
            }
            return call
        }
    }

}