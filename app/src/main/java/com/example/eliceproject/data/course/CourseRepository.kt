package com.example.eliceproject.data.course

import androidx.paging.Pager
import com.example.eliceproject.data.course.model.Course
import com.example.eliceproject.data.course.model.CourseType
import com.example.eliceproject.data.course.model.Lecture

interface CourseRepository {
    // 과목 리스트 불러오기
    fun getCourseList(
        type: CourseType,
        pageSize: Int = 10,
    ): Pager<Int, Course>

    // 과목 리스트 불러오기
    suspend fun getCourseDetail(
        courseId: Int,
    ): Course

    // 수업 리스트 불러오기
    fun getLectureList(
        pageSize: Int = 10,
    ): Pager<Int, Lecture>
}
