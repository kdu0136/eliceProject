package com.example.eliceproject.view.fragment.home.components.view_holder

import com.example.eliceproject.data.course.model.Course
import com.example.eliceproject.databinding.HolderCourseListBinding
import com.example.eliceproject.view.global_components.view_holder.BaseViewHolder

// 과목 리스트 뷰 홀더
class CourseListViewHolder(
    binding: HolderCourseListBinding,
    itemClick: (Course) -> Unit,
) : BaseViewHolder<HolderCourseListBinding, Course>(
    binding = binding,
    itemClick = itemClick
) {
    override fun display(item: Course) {
        with(binding) {
            holder = this@CourseListViewHolder
            data = item
            executePendingBindings()
        }
    }
}