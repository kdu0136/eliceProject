package com.example.eliceproject.data.course.model

import com.google.gson.annotations.SerializedName

data class ReqMyCourseList(
    @SerializedName("course_ids") val courseIdList: List<Int>,
)
