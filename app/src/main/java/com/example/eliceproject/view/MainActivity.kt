package com.example.eliceproject.view

import android.os.Build
import androidx.navigation.fragment.NavHostFragment
import com.example.eliceproject.R
import com.example.eliceproject.databinding.ActivityMainBinding
import com.example.eliceproject.extention.setStatusBarColor

class MainActivity :
    BaseActivity<ActivityMainBinding>(resId = R.layout.activity_main) {

    override fun onSetupUI() {
        with(binding) {
            lifecycleOwner = this@MainActivity
        }
    }

    override fun observeViewModel() {
    }

    override fun onBackButtonPressed() {
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.fragmentContainer) as? NavHostFragment
                ?: return

        // fragment stack 이 없을 경우 activity 종료
        if (navHostFragment.childFragmentManager.backStackEntryCount == 0) {
            finish()
        } else { // fragment stack 이 있을 경우 가장 최근 fragment pop
            navHostFragment.navController.popBackStack()
        }
    }

    override fun onWindowFocusChanged(hasFocus: Boolean) {
        super.onWindowFocusChanged(hasFocus)
        // Api 23 이상 status bar 색상 흰색으로
        if (hasFocus && Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            this.setStatusBarColor(colorRes = R.color.white, isLight = true)
        }
    }
}