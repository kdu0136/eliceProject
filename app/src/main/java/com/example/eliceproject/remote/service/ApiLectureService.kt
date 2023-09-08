package com.example.eliceproject.remote.service

import com.example.eliceproject.data.lecture.model.ResLectureList
import com.example.eliceproject.data.remote.ResultWrapper
import com.example.eliceproject.remote.interfaces.LectureRequest
import com.example.eliceproject.util.PrintLog
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

interface ApiLectureService {
    // 수업 리스트 불러오기
    suspend fun requestLectureList(
        courseId: Int,
        page: Int,
        count: Int,
    ): ResultWrapper<ResLectureList>
}

class ApiLectureServiceImpl(private val lectureRequest: LectureRequest) :
    ApiLectureService {
    private val tag = javaClass.simpleName

    override suspend fun requestLectureList(
        courseId: Int,
        page: Int,
        count: Int
    ): ResultWrapper<ResLectureList> =
        withContext(Dispatchers.IO) {
            try {
                PrintLog.d(
                    "requestLectureList",
                    "courseId: $courseId, page: $page, count: $count",
                    tag
                )

                val result = lectureRequest.getLectureList(
                    courseId = courseId,
                    offset = (page - 1) * count,
                    count = count,
                )

                PrintLog.d("requestLectureList result", "$result", tag)

                ResultWrapper.statusHandle(
                    status = result.result,
                    result = result,
                )
            } catch (throwable: Throwable) {
                PrintLog.e("requestLectureList error", "$throwable", tag)
                ResultWrapper.Error(error = throwable)
            }
        }
}