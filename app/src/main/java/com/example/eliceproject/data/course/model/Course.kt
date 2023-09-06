package com.example.eliceproject.data.course.model

import androidx.recyclerview.widget.DiffUtil
import com.google.gson.annotations.SerializedName

data class Course(
    @SerializedName("id") val id: Int,
    @SerializedName("title") val title: String,
    @SerializedName("Image_file_url") val imageUrl: String?,
    @SerializedName("short_description") val description: String,
    @SerializedName("taglist") val tagList: List<String>,
) {
    companion object {
        val DiffCallback = object : DiffUtil.ItemCallback<Course>() {
            override fun areItemsTheSame(
                oldItem: Course,
                newItem: Course
            ): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(
                oldItem: Course,
                newItem: Course
            ): Boolean =
                oldItem == newItem
        }
    }
}
