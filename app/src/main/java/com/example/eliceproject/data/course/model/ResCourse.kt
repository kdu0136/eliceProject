package com.example.eliceproject.data.course.model

import com.example.eliceproject.data.remote.ResResult
import com.google.gson.annotations.SerializedName

data class ResCourse(
    @SerializedName("course") val course: Course,
    @SerializedName("_result") val result: ResResult,
)
