package com.example.eliceproject.data.course.model

import com.example.eliceproject.data.remote.ResResult
import com.google.gson.annotations.SerializedName

data class ResCourseList(
    @SerializedName("courses") val courseList: List<Course>,
    @SerializedName("course_count") val total: Int,
    @SerializedName("_result") val result: ResResult,
)
