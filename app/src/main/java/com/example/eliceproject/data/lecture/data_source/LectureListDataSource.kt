package com.example.eliceproject.data.lecture.data_source

import com.example.eliceproject.data.BasePagingSource
import com.example.eliceproject.data.PagingSourceData
import com.example.eliceproject.data.lecture.model.Lecture

class LectureListDataSource(
) : BasePagingSource<Lecture>() {

    override suspend fun getNewData(page: Int, count: Int): PagingSourceData<Lecture> {
        val result = arrayListOf<Lecture>().apply {
            if (page < 3) {
                for (i in 0 until count) {
                    val lecture = Lecture(
                        id = i,
                        title = "title $i",
                        description = "description $i",
                    )
                    add(lecture)
                }
            }
        }

        // first data 변수 할당
        if (page == 1 && result.isNotEmpty()) {
            result.first().isFirst = true
        }

        return PagingSourceData(
            data = result,
            prevPage = if (page == 1) null else page - 1,
            nextPage = if (result.size < count) null else page + 1
        )
    }
}