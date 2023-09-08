package com.example.eliceproject.remote.interfaces

import com.example.eliceproject.data.lecture.model.ResLectureList
import com.example.eliceproject.remote.ApiURL
import retrofit2.http.*

interface LectureRequest {
    @GET(ApiURL.LECTURE_LIST)
    suspend fun getLectureList(
        @Query(value = "course_id") courseId: Int,
        @Query(value = "offset") offset: Int,
        @Query(value = "count") count: Int,
    ): ResLectureList
}