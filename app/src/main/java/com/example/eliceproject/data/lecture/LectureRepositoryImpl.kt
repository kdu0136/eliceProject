package com.example.eliceproject.data.lecture

import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.example.eliceproject.data.lecture.data_source.LectureListDataSource
import com.example.eliceproject.data.lecture.model.Lecture
import com.example.eliceproject.remote.service.ApiLectureService

class LectureRepositoryImpl(
    private val apiLectureService: ApiLectureService,
) : LectureRepository {
    override fun getLectureList(
        courseId: Int,
        pageSize: Int,
    ): Pager<Int, Lecture> =
        Pager(
            PagingConfig(
                initialLoadSize = pageSize,
                pageSize = pageSize,
            )
        ) {
            LectureListDataSource(
                apiLectureService = apiLectureService,
                courseId = courseId,
            )
        }
}
