package com.example.eliceproject.data.course

import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.example.eliceproject.data.course.data_source.CourseListDataSource
import com.example.eliceproject.data.course.model.Course
import com.example.eliceproject.data.course.model.CourseType
import com.example.eliceproject.data.remote.ResultWrapper
import com.example.eliceproject.remote.service.ApiCourseService

class CourseRepositoryImpl(
    private val apiCourseService: ApiCourseService,
) : CourseRepository {
    override fun getCourseList(
        type: CourseType,
        pageSize: Int,
    ): Pager<Int, Course> =
        Pager(
            PagingConfig(
                initialLoadSize = pageSize,
                pageSize = pageSize,
            )
        ) {
            CourseListDataSource(
                apiCourseService = apiCourseService,
                type = type,
            )
        }

    override suspend fun getCourseDetail(courseId: Int): Course =
        when (val result = apiCourseService.requestCourseDetail(
            courseId = courseId
        )) {
            is ResultWrapper.Success -> result.result.course
            is ResultWrapper.Error -> throw result.error
        }
}
