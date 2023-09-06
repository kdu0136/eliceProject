package com.example.eliceproject.view

import android.os.Build
import com.example.eliceproject.R
import com.example.eliceproject.databinding.ActivityMainBinding
import com.example.eliceproject.extention.setStatusBarColor
import com.example.eliceproject.util.PrintLog

class MainActivity :
    BaseActivity<ActivityMainBinding>(resId = R.layout.activity_main) {

    override fun onSetupUI() {
        with (binding) {
            PrintLog.d("test", "")
        }
    }

    override fun observeViewModel() {
    }

    override fun onBackButtonPressed() {
    }

    override fun onWindowFocusChanged(hasFocus: Boolean) {
        super.onWindowFocusChanged(hasFocus)
        // Api 23 이상 status bar 색상 흰색으로
        if (hasFocus && Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            this.setStatusBarColor(colorRes = R.color.white, isLight = true)
        }
    }
}