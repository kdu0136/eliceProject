package com.example.eliceproject.data.course

import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.example.eliceproject.data.course.dao.MyCourseDao
import com.example.eliceproject.data.course.data_source.CourseListDataSource
import com.example.eliceproject.data.course.model.Course
import com.example.eliceproject.data.course.model.CourseType
import com.example.eliceproject.data.course.model.MyCourse
import com.example.eliceproject.data.remote.ResultWrapper
import com.example.eliceproject.remote.service.ApiCourseService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class CourseRepositoryImpl(
    private val apiCourseService: ApiCourseService,
    private val myCourseDao: MyCourseDao,
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

    override suspend fun getIsExistCourse(courseId: Int): Boolean =
        withContext(Dispatchers.IO) {
            myCourseDao.existCourse(courseId = courseId)
        }

    override suspend fun updateMyCourse(courseId: Int): Boolean = withContext(Dispatchers.IO) {
        val existCourse = myCourseDao.existCourse(courseId = courseId)
        if (existCourse) {// 이미 추가되어있는 course - 삭제
            myCourseDao.deleteMyCourse(courseId = courseId)
            false
        } else {// 없는 course - 추가
            myCourseDao.insertMyCourse(course = MyCourse(id = courseId))
            true
        }
    }
}
