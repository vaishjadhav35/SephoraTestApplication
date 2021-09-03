package com.example.sephoraapptestapplication.baseclasses

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel

open class BaseViewModel(private val repository: BaseRepository) : ViewModel() {

    init {
        if (!repository.errorResponse.hasObservers()) observeErrorResponse()
    }

    private val errorResponse =
        MutableLiveData<com.example.sephoraapptestapplication.network.Error>()

    open fun observeErrorResponse() {
        repository.errorResponse.observeForever(Observer { response ->
            errorResponse.value = response
        })
    }

    fun getErrorResponse(): MutableLiveData<com.example.sephoraapptestapplication.network.Error> {
        return errorResponse
    }
}