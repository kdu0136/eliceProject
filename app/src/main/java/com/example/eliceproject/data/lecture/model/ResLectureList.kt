package com.example.eliceproject.data.lecture.model

import com.example.eliceproject.data.remote.ResResult
import com.google.gson.annotations.SerializedName

data class ResLectureList(
    @SerializedName("lectures") val lectureList: ArrayList<Lecture>,
    @SerializedName("lecture_count") val total: Int,
    @SerializedName("_result") val result: ResResult,
)
