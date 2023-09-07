package com.example.eliceproject.data.course

import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.example.eliceproject.data.course.data_source.CourseListDataSource
import com.example.eliceproject.data.course.model.Course
import com.example.eliceproject.data.course.model.CourseType

class CourseRepositoryImpl(
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
                type = type,
            )
        }
}
