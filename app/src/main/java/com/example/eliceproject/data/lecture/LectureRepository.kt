package com.example.eliceproject.data.lecture

import androidx.paging.Pager
import com.example.eliceproject.data.lecture.model.Lecture

interface LectureRepository {
    // 수업 리스트 불러오기
    fun getLectureList(
        pageSize: Int = 10,
    ): Pager<Int, Lecture>
}
