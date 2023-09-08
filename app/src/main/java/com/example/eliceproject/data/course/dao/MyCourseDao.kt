package com.example.eliceproject.data.course.dao

import androidx.room.*
import com.example.eliceproject.data.course.model.MyCourse

@Dao
interface MyCourseDao {
    // limit & offset 에 해당하는 id list 조회
    @Query("SELECT id FROM my_course LIMIT :limit OFFSET :offset")
    fun getMyCourseIdList(limit: Int, offset: Int): List<Int>

    // course id 에 해당하는 selected 값 가져오기
    @Query("SELECT EXISTS(SELECT id FROM my_course WHERE id = :courseId)")
    fun existCourse(courseId: Int): Boolean

    // 중복 값 충돌 발생 시 새로 들어온 데이터로 교체.
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMyCourse(course: MyCourse)

    @Query("DELETE FROM my_course WHERE id = :courseId")
    fun deleteMyCourse(courseId: Int)
}