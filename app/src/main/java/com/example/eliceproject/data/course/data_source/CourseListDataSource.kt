package com.example.eliceproject.data.course.data_source

import com.example.eliceproject.data.BasePagingSource
import com.example.eliceproject.data.PagingSourceData
import com.example.eliceproject.data.course.model.Course
import com.example.eliceproject.data.course.model.CourseType
import com.example.eliceproject.data.remote.ResultWrapper
import com.example.eliceproject.remote.service.ApiCourseService

class CourseListDataSource(
    private val apiCourseService: ApiCourseService,
    private val type: CourseType,
) : BasePagingSource<Course>() {

    override suspend fun getNewData(page: Int, count: Int): PagingSourceData<Course> {
        val data = apiCourseService.requestCourseList(
            type = type,
            page = page,
            count = count,
        ).let {
            when (it) {
                is ResultWrapper.Success -> it.result.courseList
                is ResultWrapper.Error -> throw it.error
            }
        }

        return PagingSourceData(
            data = data,
            prevPage = if (page == 1) null else page - 1,
            nextPage = if (data.size < count) null else page + 1 // 요청 count 보다 size 가 작으면 next page 없음
        )
    }
}