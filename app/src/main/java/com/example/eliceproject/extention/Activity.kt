package com.example.eliceproject.extention

import android.app.Activity
import android.os.Build
import android.view.View
import android.view.WindowInsetsController
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat

// status bar light / dark mode 설정
@RequiresApi(Build.VERSION_CODES.M)
@Suppress("DEPRECATION")
fun Activity.setStatusBarColor(colorRes: Int, isLight: Boolean) {
    window.statusBarColor = ContextCompat.getColor(this, colorRes)

    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
        window.insetsController?.setSystemBarsAppearance(
            if (isLight) WindowInsetsController.APPEARANCE_LIGHT_STATUS_BARS else 0, // value
            WindowInsetsController.APPEARANCE_LIGHT_STATUS_BARS, // mask
        )
    } else {
        val lFlags = window.decorView.systemUiVisibility
        window.decorView.systemUiVisibility =
            if (isLight) lFlags or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
            else lFlags and View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR.inv()
    }
}
