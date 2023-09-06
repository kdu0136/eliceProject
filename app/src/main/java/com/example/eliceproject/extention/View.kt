package com.example.eliceproject.extention

import android.os.SystemClock
import android.view.View
import androidx.databinding.BindingAdapter


/**
 * view 에 safe click 이벤트 등록
 */
@BindingAdapter("viewBind:onSafeClick")
fun View.setSafeOnClickListener(onClick: View.OnClickListener?) {
    setOnClickListener(SafeClickListener(onSafeClick = onClick))
}

// click interval delay 가지는 click listener
private class SafeClickListener(
    private val defaultInterval: Int = 200,
    private val onSafeClick: View.OnClickListener?
) : View.OnClickListener {
    private var lastTimeClicked: Long = 0
    override fun onClick(v: View?) {
        if (SystemClock.elapsedRealtime() - lastTimeClicked < defaultInterval || v == null || !v.isEnabled)
            return
        lastTimeClicked = SystemClock.elapsedRealtime()
        onSafeClick?.onClick(v)
    }
}
