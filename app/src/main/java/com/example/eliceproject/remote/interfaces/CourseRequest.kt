package com.example.eliceproject.remote.interfaces

import com.example.eliceproject.data.course.model.ResCourse
import com.example.eliceproject.data.course.model.ResCourseList
import com.example.eliceproject.remote.ApiURL
import retrofit2.http.*

interface CourseRequest {
    @GET(ApiURL.COURSE_LIST)
    suspend fun getCourseList(
        @Query(value = "filter_is_free") freeFilter: Boolean?,
        @Query(value = "filter_is_recommended") recommendFilter: Boolean?,
        @Query(value = "offset") offset: Int,
        @Query(value = "count") count: Int,
    ): ResCourseList

    @GET(ApiURL.COURSE_DETAIL)
    suspend fun getCourseDetail(
        @Query(value = "course_id") courseId: Int,
    ): ResCourse
}