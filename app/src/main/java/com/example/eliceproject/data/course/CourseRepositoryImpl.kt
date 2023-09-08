package com.example.eliceproject.data.course

import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.example.eliceproject.data.course.data_source.CourseListDataSource
import com.example.eliceproject.data.course.model.Course
import com.example.eliceproject.data.course.model.CourseType
import kotlinx.coroutines.delay
import kotlin.random.Random

class CourseRepositoryImpl(
) : CourseRepository {
    override fun getCourseList(
        type: CourseType,
        pageSize: Int,
    ): Pager<Int, Course> =
        Pager(
            PagingConfig(
                initialLoadSize = pageSize,
                pageSize = pageSize,
            )
        ) {
            CourseListDataSource(
                type = type,
            )
        }

    override suspend fun getCourseDetail(courseId: Int): Course {
        val imageUrl = Random.nextBoolean().let { if(it) "" else "https://cdn-api.elice.io/api/file/f0b83d1dfd5e47f4b6e8e31753813eeb/%E1%84%8B%E1%85%B0%E1%86%B8_%E1%84%83%E1%85%A9%E1%84%85%E1%85%A6%E1%84%86%E1%85%B52.png?se=2023-09-21T00%3A15%3A00Z&sp=r&sv=2021-12-02&sr=b&sig=x0FMZROy8O4xnQVJZ2Fl2qfB3OvxQntK80j9w4/M9GM%3D" }
        return Course(
            id = courseId,
            title = "코딩 기초! 도레미 파이썬 vol2",
            imageUrl = imageUrl,
            shortDescription = "shortDescription $courseId",
            description = "\"### **파이썬 기초에서 한 걸음 더! 배운 것을 응용하는 법을 배워요.**\\r\\n파이썬의 기초 자료형, 조건문, 리스트와 반복문을 배우셨나요? 이번에는 코드를 짜는 데에 어떻게 이들을 활용하는지 배워봅시다! 더 많은 파이썬의 자료형에서부터 객체 지향 프로그래밍에 대한 기본 개념까지 학습해봐요.\\r\\n <br>\\r\\n### **실습을 통해 파이썬 함수와 자연스레 친해지세요.**\\r\\n프로그래밍에서 정~말 중요한 함수. 함수를 사용하면 반복되는 작업을 하지 않아도 될 뿐만 아니라 프로그램을 한눈에 파악하기 좋아져요. 이 과목에서는 직접 함수를 만들고 만든 함수를 모듈에 저장해 불러오는 법을 실습을 통해 익힙니다. 출퇴근길, 등하굣길 30분씩만 실습에 투자하면 자연스레 함수와 친해질 수 있어요. 유능한 프로그래머에 한 걸음 더 가까워지세요!\\r\\n <br>\\r\\n### **객체지향 프로그래밍을 위한 디딤돌!**\\r\\n빅데이터 시대에 더욱 중요한 '객체'! 효율적으로 데이터를 처리하기 위해서는 각자의 역할을 수행하는 '객체'가 꼭 필요해요. 이 과목에서 객체지향 프로그래밍의 기본 개념을 익히고 객체지향 프로그래밍을 위한 기초 체력을 탄탄히 해보세요!\"",
            tagList = ArrayList<String>().apply {
                val max = Random.nextInt(4, 10)
                for (j in 0 until max) {
                    add("tag$j")
                }
            }
        )
    }
}
