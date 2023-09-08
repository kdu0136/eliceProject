package com.example.eliceproject.data.course.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "my_course")
data class MyCourse(
    @PrimaryKey
    @SerializedName("id") val id: Int,
)
