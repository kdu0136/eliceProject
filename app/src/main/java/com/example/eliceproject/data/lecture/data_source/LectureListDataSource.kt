package com.example.eliceproject.data.lecture.data_source

import com.example.eliceproject.data.BasePagingSource
import com.example.eliceproject.data.PagingSourceData
import com.example.eliceproject.data.lecture.model.Lecture
import com.example.eliceproject.data.remote.ResultWrapper
import com.example.eliceproject.remote.service.ApiLectureService

class LectureListDataSource(
    private val apiLectureService: ApiLectureService,
    private val courseId: Int,
) : BasePagingSource<Lecture>() {

    override suspend fun getNewData(page: Int, count: Int): PagingSourceData<Lecture> {
        val data = apiLectureService.requestLectureList(
            courseId = courseId,
            page = page,
            count = count,
        ).let {
            when (it) {
                is ResultWrapper.Success -> it.result.lectureList
                is ResultWrapper.Error -> throw it.error
            }
        }

        // first data 변수 할당
        if (page == 1 && data.isNotEmpty()) {
            data.first().isFirst = true
        }

        return PagingSourceData(
            data = data,
            prevPage = if (page == 1) null else page - 1,
            nextPage = if (data.size < count) null else page + 1 // 요청 count 보다 size 가 작으면 next page 없음
        )
    }
}