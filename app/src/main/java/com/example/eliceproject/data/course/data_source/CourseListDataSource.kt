package com.example.eliceproject.data.course.data_source

import com.example.eliceproject.data.BasePagingSource
import com.example.eliceproject.data.PagingSourceData
import com.example.eliceproject.data.course.model.Course
import com.example.eliceproject.data.course.model.CourseType
import com.example.eliceproject.util.PrintLog
import kotlin.random.Random

class CourseListDataSource(
    private val type: CourseType,
) : BasePagingSource<Course>() {

    override suspend fun getNewData(page: Int, count: Int): PagingSourceData<Course> {
        val result = arrayListOf<Course>()
        for (i in 0 until 10) {
            val course = Course(
                id = i,
                title = "title $i",
                imageUrl = null,
                logoUrl = "",
                shortDescription = "shortDescription $i",
                description = "description $i",
                tagList = ArrayList<String>().apply {
                    val max = Random.nextInt(4, 10)
                    for (j in 0 until max) {
                        add("tag$j")
                    }
                }
            )
            result.add(course)
        }

        return PagingSourceData(
            data = result,
            prevPage = if (page == 1) null else page - 1,
            nextPage = if (result.size < count) null else page + 1
        )
    }
}