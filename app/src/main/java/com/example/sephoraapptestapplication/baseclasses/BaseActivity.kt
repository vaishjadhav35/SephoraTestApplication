package com.example.sephoraapptestapplication.baseclasses

import android.view.View
import android.widget.TextView
import android.widget.Toast
import com.example.sephoraapptestapplication.R
import com.google.android.material.snackbar.Snackbar
import dagger.android.support.DaggerAppCompatActivity
import java.net.UnknownHostException

abstract class BaseActivity : DaggerAppCompatActivity() {

    var progressDialog: BaseDialog? = null

    fun showProgressDialog(message: String) {
        getProgressDialogInstance()?.let {
            var progressDialog = it
            progressDialog.show()
        }
        progressDialog?.findViewById<TextView>(R.id.progress_msg).apply { this?.text = message }

    }

    private fun getProgressDialogInstance(): BaseDialog? {
        progressDialog?.let {
            return it
        }

        progressDialog = BaseDialog(this, false)
        return progressDialog
    }

    fun dismissProgressDialog() {
        getProgressDialogInstance()?.let {
            var progressDialog = it
            if (progressDialog.isShowing)
                progressDialog.dismiss()
        }
    }

    fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    fun observeErrorResponse(
        viewModel: BaseViewModel,
        view: View
    ) {
        viewModel.getErrorResponse().observe(this, androidx.lifecycle.Observer { response ->
            dismissProgressDialog()
            if (response.errorType is UnknownHostException) {
                val snackbar = Snackbar.make(
                    view,
                    getString(R.string.no_internet_connection),
                    Snackbar.LENGTH_INDEFINITE
                )
                snackbar.show()
            }

        })
    }
}