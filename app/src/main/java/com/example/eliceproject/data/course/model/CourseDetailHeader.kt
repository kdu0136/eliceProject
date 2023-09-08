package com.example.eliceproject.data.course.model

import androidx.recyclerview.widget.DiffUtil
import com.example.eliceproject.view.global_components.view_holder.ViewHolderType

sealed class CourseDetailHeader {
    data class TitleWithBanner(
        val title: String,
        val logoUrl: String,
        val bannerUrl: String,
    ): CourseDetailHeader()

    data class TitleWithoutBanner(
        val title: String,
        val logoUrl: String,
        val bannerUrl: String,
        val shortDescription: String,
    ): CourseDetailHeader()

    data class Description(
        val description: String,
    ): CourseDetailHeader()

    companion object {
        val DiffCallback = object : DiffUtil.ItemCallback<CourseDetailHeader>() {
            override fun areItemsTheSame(
                oldItem: CourseDetailHeader,
                newItem: CourseDetailHeader
            ): Boolean =
                oldItem == newItem

            override fun areContentsTheSame(
                oldItem: CourseDetailHeader,
                newItem: CourseDetailHeader
            ): Boolean =
                oldItem == newItem
        }
    }
}
