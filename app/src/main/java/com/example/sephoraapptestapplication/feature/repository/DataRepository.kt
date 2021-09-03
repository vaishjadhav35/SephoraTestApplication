package com.example.sephoraapptestapplication.feature.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.sephoraapptestapplication.baseclasses.BaseRepository
import com.example.sephoraapptestapplication.model.FetchRepositoryResponse
import com.example.sephoraapptestapplication.network.ApiInterface
import com.example.sephoraapptestapplication.network.Constants
import com.example.sephoraapptestapplication.network.ResponseWrapper
import javax.inject.Inject

class DataRepository @Inject constructor(val service: ApiInterface): BaseRepository() {
    fun fetchRepositoryData(
        apiResponse: MutableLiveData<ResponseWrapper>,
        serviceID: String,
        request:Any?
    ) {
        this.apiResponse = apiResponse

        networkCall(
            Constants.ApiMethod.GET_METHOD,
            Constants.REPOSITORIES_URL, request, serviceID,
            FetchRepositoryResponse::class.java)
    }

    override fun handleResponse(responseObj: ResponseWrapper) {
        apiResponse.value = responseObj
    }

    override fun handleError(error: Throwable) {
        Log.e("Error", error.message.toString())
    }

}