package com.example.eliceproject.data.course.model

import androidx.recyclerview.widget.DiffUtil
import com.google.gson.annotations.SerializedName

data class Lecture(
    @SerializedName("id") val id: Int,
    @SerializedName("title") val title: String?,
    @SerializedName("description") val description: String?,
) {
    companion object {
        val DiffCallback = object : DiffUtil.ItemCallback<Lecture>() {
            override fun areItemsTheSame(
                oldItem: Lecture,
                newItem: Lecture
            ): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(
                oldItem: Lecture,
                newItem: Lecture
            ): Boolean =
                oldItem == newItem
        }
    }
}
