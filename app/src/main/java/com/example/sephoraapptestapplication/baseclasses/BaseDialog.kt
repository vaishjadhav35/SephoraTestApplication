package com.example.sephoraapptestapplication.baseclasses

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.View
import android.view.Window
import android.view.WindowManager
import com.example.sephoraapptestapplication.R

class BaseDialog (context: Context, isCancellable: Boolean = true) :
    Dialog(context, R.style.ThemeOverlay_AppCompat_Dialog_Alert) {

    private var isCancellable = isCancellable

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.custom_progress_dialog)
        setCancelable(isCancellable)
    }

    override fun show() {
        window?.setFlags(
            WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,
            WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE
        )
        super.show()
        window?.clearFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE)
        window?.decorView.apply {
            this?.systemUiVisibility =
                View.SYSTEM_UI_FLAG_HIDE_NAVIGATION or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
        }
    }
}