package com.example.eliceproject.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.eliceproject.data.course.dao.MyCourseDao
import com.example.eliceproject.data.course.model.MyCourse

@Database(entities = [MyCourse::class], version = 1)
abstract class EliceDataBase : RoomDatabase() {
    abstract fun myCourseDao(): MyCourseDao
}