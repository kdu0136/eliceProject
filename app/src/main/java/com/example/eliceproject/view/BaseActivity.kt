package com.example.eliceproject.view

import android.os.Bundle
import androidx.activity.OnBackPressedCallback
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.example.eliceproject.util.PrintLog

abstract class BaseActivity<VB : ViewDataBinding>(@LayoutRes resId: Int) :
    AppCompatActivity() {
    protected val activityTag: String = javaClass.simpleName

    protected val binding: VB by lazy { DataBindingUtil.setContentView(this, resId) }

    /**
     * UI setup
     */
    abstract fun onSetupUI()

    /**
     * view model observe
     */
    abstract fun observeViewModel()

    private val backPressedCallBack: OnBackPressedCallback = object : OnBackPressedCallback(true) {
        override fun handleOnBackPressed() = onBackButtonPressed()
    }

    abstract fun onBackButtonPressed()

    override fun onCreate(savedInstanceState: Bundle?) {
        printActivityLifecycle(name = object {}.javaClass.enclosingMethod?.name.toString())
        super.onCreate(savedInstanceState)

        this.onBackPressedDispatcher.addCallback(this, backPressedCallBack)

        onSetupUI()

        observeViewModel()
    }

    override fun onStart() {
        printActivityLifecycle(name = object {}.javaClass.enclosingMethod?.name.toString())
        super.onStart()
    }

    override fun onResume() {
        printActivityLifecycle(name = object {}.javaClass.enclosingMethod?.name.toString())
        super.onResume()
    }

    override fun onPause() {
        printActivityLifecycle(name = object {}.javaClass.enclosingMethod?.name.toString())
        super.onPause()
    }

    override fun onRestart() {
        printActivityLifecycle(name = object {}.javaClass.enclosingMethod?.name.toString())
        super.onRestart()
    }

    override fun onStop() {
        printActivityLifecycle(name = object {}.javaClass.enclosingMethod?.name.toString())
        super.onStop()
    }

    override fun onDestroy() {
        printActivityLifecycle(name = object {}.javaClass.enclosingMethod?.name.toString())
        super.onDestroy()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        printActivityLifecycle(name = object {}.javaClass.enclosingMethod?.name.toString())
        super.onSaveInstanceState(outState)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        printActivityLifecycle(name = object {}.javaClass.enclosingMethod?.name.toString())
        super.onRestoreInstanceState(savedInstanceState)
    }

    private fun printActivityLifecycle(name: String) {
        PrintLog.d(javaClass.simpleName, name, activityTag)
    }
}