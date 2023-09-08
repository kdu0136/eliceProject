package com.example.eliceproject.data.course

import androidx.paging.Pager
import com.example.eliceproject.data.course.model.Course
import com.example.eliceproject.data.course.model.CourseType

interface CourseRepository {
    // type(free, recommend)에 따른 과목 리스트 불러오기
    fun getCourseList(
        type: CourseType,
        pageSize: Int = 10,
    ): Pager<Int, Course>

    // 과목 상세 불러오기
    suspend fun getCourseDetail(
        courseId: Int,
    ): Course

    // 내 학습에 추가 여부 불러오기
    suspend fun getIsExistCourse(courseId: Int): Boolean

    // 내 학습 유무 변경 (이미 추가되어있는 course 면 삭제 & 없으면 추가)
    // return => true - 추가 / false - 삭제
    suspend fun updateMyCourse(courseId: Int): Boolean

    // 내 학습 리스트 불러오기
    fun getMyCourseList(
        pageSize: Int = 10,
    ): Pager<Int, Course>
}
