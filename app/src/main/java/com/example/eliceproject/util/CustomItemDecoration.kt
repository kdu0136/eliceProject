package com.example.eliceproject.util

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.eliceproject.extention.dipToPx

// recycler view item off set 설정
class CustomItemDecoration : RecyclerView.ItemDecoration {
    private val leftOffSet: Int
    private val rightOffSet: Int
    private val topOffSet: Int
    private val bottomOffSet: Int

    // 전체 offset
    constructor(offset: Float = 0f) {
        val offSetDIP = offset.dipToPx.toInt()

        this.leftOffSet = offSetDIP
        this.rightOffSet = offSetDIP
        this.topOffSet = offSetDIP
        this.bottomOffSet = offSetDIP
    }

    // 개별 offset
    constructor(
        left: Float = 0f,
        right: Float = 0f,
        top: Float = 0f,
        bottom: Float = 0f,
    ) {
        this.leftOffSet = left.dipToPx.toInt()
        this.rightOffSet = right.dipToPx.toInt()
        this.topOffSet = top.dipToPx.toInt()
        this.bottomOffSet = bottom.dipToPx.toInt()
    }

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        recyclerView: RecyclerView,
        state: RecyclerView.State
    ) {
        with(outRect) {
            left = leftOffSet
            right = rightOffSet
            top = topOffSet
            bottom = bottomOffSet
        }
    }
}
