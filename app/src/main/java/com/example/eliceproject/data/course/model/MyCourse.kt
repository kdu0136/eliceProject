package com.example.eliceproject.data.course.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "my_course")
data class MyCourse(
    @PrimaryKey val id: Int,
    val registerDate: Long
)
