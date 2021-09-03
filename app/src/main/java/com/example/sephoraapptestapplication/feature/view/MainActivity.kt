package com.example.sephoraapptestapplication.feature.view

import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sephoraapptestapplication.R
import com.example.sephoraapptestapplication.baseclasses.BaseActivity
import com.example.sephoraapptestapplication.feature.viewmodel.DataViewModel
import com.example.sephoraapptestapplication.model.FetchRepositoryResponse
import com.example.sephoraapptestapplication.network.Constants.Companion.REPOSITORIES_SERVICE_ID
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject


class MainActivity : BaseActivity() {
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    lateinit var dataListAdapter: DataListAdapter

    private val dataViewModel by lazy {
        ViewModelProvider(this, viewModelFactory).get(DataViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (!dataViewModel.apiResponse.hasObservers()) observeApiResponse()
        if (!dataViewModel.getErrorResponse().hasObservers()) observeErrorResponse(
            dataViewModel,
            recyclerView
        )
        showProgressDialog("Loading...")
        dataViewModel.fetchRepositoryDetailsApi()
    }

    private fun observeApiResponse() {
        dataViewModel.apiResponse.observe(this, Observer { apiResponse ->
            dismissProgressDialog()
            if (!apiResponse.isSuccess) {
                showToast(apiResponse.message)
            } else {
                when (apiResponse.serviceID) {
                    REPOSITORIES_SERVICE_ID -> {
                        if (apiResponse.result != null) {
                            var data = apiResponse.result as FetchRepositoryResponse
                            var repositoryArrayList = data.included
                            dataListAdapter = DataListAdapter(this, repositoryArrayList)
                            recyclerView.apply {
                                layoutManager = LinearLayoutManager(context)
                                adapter = dataListAdapter
                            }
                            recyclerView.adapter = dataListAdapter
                            dataListAdapter.notifyDataSetChanged()
                        }

                    }

                }
            }
        })

    }
}