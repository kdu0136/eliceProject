package com.example.eliceproject.data.lecture.model

import androidx.recyclerview.widget.DiffUtil
import com.example.eliceproject.view.global_components.view_holder.ViewHolderType
import com.google.gson.annotations.SerializedName

data class Lecture(
    @SerializedName("id") val id: Int,
    @SerializedName("title") val title: String?,
    @SerializedName("description") val description: String?,
) {
    var type: ViewHolderType? = null
    var isFirst: Boolean = false
    var isLast: Boolean = false

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

        fun emptyData(): Lecture = Lecture(
            id = -1,
            title = null,
            description = null,
        )
    }
}
