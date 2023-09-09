package com.example.eliceproject.data.course.data_source

import com.example.eliceproject.data.BasePagingSource
import com.example.eliceproject.data.PagingSourceData
import com.example.eliceproject.data.course.dao.MyCourseDao
import com.example.eliceproject.data.course.model.Course
import com.example.eliceproject.data.remote.ResultWrapper
import com.example.eliceproject.remote.service.ApiCourseService

class MyCourseListDataSource(
    private val apiCourseService: ApiCourseService,
    private val myCourseDao: MyCourseDao,
) : BasePagingSource<Course>() {

    override suspend fun getNewData(page: Int, count: Int): PagingSourceData<Course> {
        val courseIdList = myCourseDao.getMyCourseIdList(
            offset = (page - 1) * count,
            limit = count,
        )

        val data = apiCourseService.requestCourseListByIdList(
            courseIdList = courseIdList,
        ).let {
            when (it) {
                is ResultWrapper.Success -> it.result.courseList
                is ResultWrapper.Error -> throw it.error
            }
        }

        // local 에 저장된 순서로 재정렬
        val sortedData = data.sortedBy { courseIdList.indexOf(it.id) }

        return PagingSourceData(
            data = sortedData,
            prevPage = if (page == 1) null else page - 1,
            nextPage = if (data.size < count) null else page + 1 // 요청 count 보다 size 가 작으면 next page 없음
        )
    }
}