package com.example.eliceproject.data.lecture.data_source

import com.example.eliceproject.data.BasePagingSource
import com.example.eliceproject.data.PagingSourceData
import com.example.eliceproject.data.lecture.model.Lecture
import com.example.eliceproject.data.remote.ResultWrapper
import com.example.eliceproject.remote.service.ApiLectureService
import com.example.eliceproject.view.global_components.view_holder.ViewHolderType

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
                is ResultWrapper.Success -> it.result.lectureList.also { lectureList ->
                    // first data 변수 할당 & header 정의
                    if (page == 1 && lectureList.isNotEmpty()) {
                        lectureList.first().isFirst = true
                        lectureList.add(0, Lecture.emptyData().apply {
                            type = ViewHolderType.HEADER
                        })
                    }
                    // data type body 할당
                    lectureList
                        .filter { lecture -> lecture.type != ViewHolderType.HEADER }
                        .map { lecture -> lecture.type = ViewHolderType.BODY }
                }
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