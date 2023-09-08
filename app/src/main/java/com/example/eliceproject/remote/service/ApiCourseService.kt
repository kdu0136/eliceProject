package com.example.eliceproject.remote.service

import com.example.eliceproject.data.course.model.CourseType
import com.example.eliceproject.data.course.model.ReqMyCourseList
import com.example.eliceproject.data.course.model.ResCourse
import com.example.eliceproject.data.course.model.ResCourseList
import com.example.eliceproject.data.remote.ResultWrapper
import com.example.eliceproject.remote.interfaces.CourseRequest
import com.example.eliceproject.util.PrintLog
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext

interface ApiCourseService {
    // 과목 리스트 불러오기
    suspend fun requestCourseList(
        type: CourseType,
        page: Int,
        count: Int,
    ): ResultWrapper<ResCourseList>

    // 특정 과목 리스트 불러오기
    suspend fun requestCourseListByIdList(
        courseIdList: List<Int>,
    ): ResultWrapper<ResCourseList>

    // 과목 상세 불러오기
    suspend fun requestCourseDetail(
        courseId: Int,
    ): ResultWrapper<ResCourse>
}

class ApiCourseServiceImpl(private val courseRequest: CourseRequest) :
    ApiCourseService {
    private val tag = javaClass.simpleName

    override suspend fun requestCourseList(
        type: CourseType,
        page: Int,
        count: Int
    ): ResultWrapper<ResCourseList> =
        withContext(Dispatchers.IO) {
            try {
                PrintLog.d(
                    "requestCourseList",
                    "type: $type, page: $page, count: $count",
                    tag
                )

                val result = courseRequest.getCourseList(
                    freeFilter = if (type == CourseType.FREE) true else null,
                    recommendFilter = if (type == CourseType.RECOMMEND) true else null,
                    courseIdListJsonStr = null,
                    offset = (page - 1) * count,
                    count = count,
                )

                PrintLog.d("requestCourseList result", "$result", tag)

                ResultWrapper.statusHandle(
                    status = result.result,
                    result = result,
                )
            } catch (throwable: Throwable) {
                PrintLog.e("requestCourseList error", "$throwable", tag)
                ResultWrapper.Error(error = throwable)
            }
        }

    override suspend fun requestCourseListByIdList(courseIdList: List<Int>): ResultWrapper<ResCourseList> =
        withContext(Dispatchers.IO) {
            try {
                PrintLog.d(
                    "requestCourseListByIdList",
                    "courseIdList: $courseIdList",
                    tag
                )

                val courseIdListJsonStr =
                    Gson().toJson(ReqMyCourseList(courseIdList = courseIdList))

                val result = courseRequest.getCourseList(
                    freeFilter = null,
                    recommendFilter = null,
                    courseIdListJsonStr = courseIdListJsonStr,
                    offset = 0,
                    count = courseIdList.size,
                )

                PrintLog.d("requestCourseListByIdList result", "$result", tag)

                ResultWrapper.statusHandle(
                    status = result.result,
                    result = result,
                )
            } catch (throwable: Throwable) {
                PrintLog.e("requestCourseListByIdList error", "$throwable", tag)
                ResultWrapper.Error(error = throwable)
            }
        }

    override suspend fun requestCourseDetail(courseId: Int): ResultWrapper<ResCourse> =
        withContext(Dispatchers.IO) {
            try {
                PrintLog.d(
                    "requestCourseDetail",
                    "courseId: $courseId",
                    tag
                )

                val result = courseRequest.getCourseDetail(
                    courseId = courseId,
                )

                PrintLog.d("requestCourseDetail result", "$result", tag)

                ResultWrapper.statusHandle(
                    status = result.result,
                    result = result,
                )
            } catch (throwable: Throwable) {
                PrintLog.e("requestCourseDetail error", "$throwable", tag)
                ResultWrapper.Error(error = throwable)
            }
        }
}