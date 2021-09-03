package com.example.sephoraapptestapplication.baseclasses

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.sephoraapptestapplication.network.ApiInterface
import com.example.sephoraapptestapplication.network.ResponseWrapper
import com.google.gson.Gson
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import org.json.JSONArray
import org.json.JSONObject
import javax.inject.Inject

abstract class BaseRepository {

    @Inject
    lateinit var apiInterface: ApiInterface
    internal var disposable: Disposable? = null
    open lateinit var apiResponse: MutableLiveData<ResponseWrapper>
    open val errorResponse = MutableLiveData<com.example.sephoraapptestapplication.network.Error>()

    fun networkCall(
        apiMethod: Int,
        url: String,
        requestObj: Any?,
        serviceID: String,
        resultClass: Class<*>
    ) {
        ApiInterface.callBack(
            apiInterface,
            apiMethod,
            url
        )
            ?.subscribeOn(Schedulers.newThread())
            ?.observeOn(AndroidSchedulers.mainThread())
            ?.subscribe(object : Observer<Any> {
                override fun onComplete() {
                    //empty implementation
                }

                override fun onSubscribe(d: Disposable) {
                    //empty implementation
                }

                override fun onError(error: Throwable) {
                    val errors =
                        com.example.sephoraapptestapplication.network.Error(
                            404,
                            serviceID,
                            error,
                            "error"
                        )
                    errorResponse.value = errors
                    handleError(error)
                }

                override fun onNext(response: Any) {
                    if (response != null) {
                        var result = getResultObj(response, resultClass)
                        handleResponse(
                            ResponseWrapper(
                                error = "",
                                result = result,
                                serviceID = serviceID,
                                message = "Success",
                                statusCode = 200
                            )
                        )
                    } else {
                        handleResponse(
                            ResponseWrapper(
                                error = "Something went wrong!!",
                                result = null,
                                serviceID = serviceID,
                                message = ""
                            )
                        )
                    }
                }
            })
    }


    private fun getResultObj(result: Any, resultClass: Class<*>): Any {
        val gson = Gson()
        Log.e("CLASS NAME::", "" + resultClass.canonicalName)
        val str = gson.toJson(result, result.javaClass)
        if (str.startsWith("[") && str.endsWith("]")) {
            val jsonArray = JSONArray(str)
            val resultObj: Any = gson.fromJson(jsonArray.toString(), resultClass)
            return resultObj
        } else {
            val jsonObject = JSONObject(str)
            val resultObj: Any = gson.fromJson(jsonObject.toString(), resultClass)
            return resultObj
        }
    }

    abstract fun handleResponse(responseObj: ResponseWrapper)

    abstract fun handleError(error: Throwable)
}