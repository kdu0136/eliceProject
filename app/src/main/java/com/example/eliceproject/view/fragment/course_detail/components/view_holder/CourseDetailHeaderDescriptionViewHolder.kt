package com.example.eliceproject.view.fragment.course_detail.components.view_holder

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ViewCompositionStrategy
import com.example.eliceproject.data.course.model.CourseDetailHeader
import com.example.eliceproject.databinding.HolderCourseDetailHeaderDescriptionBinding
import com.example.eliceproject.view.global_components.view_holder.BaseViewHolder
import com.mukesh.MarkDown

// 과목 상세 헤더 과목 소개 뷰 홀더
class CourseDetailHeaderDescriptionViewHolder(
    binding: HolderCourseDetailHeaderDescriptionBinding,
) : BaseViewHolder<HolderCourseDetailHeaderDescriptionBinding, CourseDetailHeader.Description>(
    binding = binding,
) {
    override fun display(item: CourseDetailHeader.Description) {
        binding.markdown.apply {
            // Dispose of the Composition when the view's LifecycleOwner is destroyed
            setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
            setContent {
                // In Compose world
                MaterialTheme {
                    MarkDown(text = item.description, modifier = Modifier.fillMaxSize())
                }
            }
        }
    }
}