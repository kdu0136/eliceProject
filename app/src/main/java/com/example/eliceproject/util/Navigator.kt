package com.example.eliceproject.util

import com.example.eliceproject.R
import java.io.Serializable

sealed class Navigator : Serializable {
    object Home : Navigator()
    class CourseDetail(val courseId: Int) : Navigator()

    fun getNavId(): Int = when (this) {
        is Home -> R.id.homeFragment
        is CourseDetail -> R.id.courseDetailFragment
    }
}
