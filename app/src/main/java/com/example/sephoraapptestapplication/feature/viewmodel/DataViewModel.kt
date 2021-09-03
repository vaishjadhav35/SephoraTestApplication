package com.example.sephoraapptestapplication.feature.viewmodel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.example.sephoraapptestapplication.baseclasses.BaseViewModel
import com.example.sephoraapptestapplication.feature.repository.DataRepository
import com.example.sephoraapptestapplication.model.FetchRepositoryApiResponse
import com.example.sephoraapptestapplication.model.FetchRepositoryResponse
import com.example.sephoraapptestapplication.network.Constants.Companion.REPOSITORIES_SERVICE_ID
import com.example.sephoraapptestapplication.network.ResponseWrapper
import javax.inject.Inject

class DataViewModel  @Inject constructor(private val repository: DataRepository) :
    BaseViewModel(repository)  {
    private val _getResponse = MutableLiveData<ResponseWrapper>()
    val apiResponse = MutableLiveData<FetchRepositoryApiResponse>()

    fun fetchRepositoryDetailsApi(){
        if (!_getResponse.hasObservers()) observerApiResponse()
        repository.fetchRepositoryData(_getResponse,REPOSITORIES_SERVICE_ID,null)
    }
    fun observerApiResponse() {
        _getResponse.observeForever(Observer { response ->
            if (response.statusCode != 200){
                var errorResponseMsg = if(response.error != null) response.error else response.message
                sendResponseToView(
                    responseString = errorResponseMsg,
                    isSuccess = false,
                    serviceID = response.serviceID
                )
            }else{
                when(response.serviceID){
                    REPOSITORIES_SERVICE_ID->{
                        sendResponseToView(responseString = response.message ,isSuccess = true, serviceID = response.serviceID, errorCode = response.errorCode,result = response.result as FetchRepositoryResponse)
                    }
                }
            }
        })
    }

    private fun sendResponseToView( responseString: String, isSuccess: Boolean, serviceID: String, errorCode: Int = 0,result: FetchRepositoryResponse?=null) {
        val responseData = FetchRepositoryApiResponse( isSuccess,responseString,errorCode,serviceID,result)
        apiResponse.value = responseData
    }
}