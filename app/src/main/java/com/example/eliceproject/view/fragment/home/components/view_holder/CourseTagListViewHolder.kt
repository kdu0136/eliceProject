package com.example.eliceproject.view.fragment.home.components.view_holder

import com.example.eliceproject.databinding.HolderCourseTagListBinding
import com.example.eliceproject.view.global_components.view_holder.BaseViewHolder

// 과목 태그 리스트 뷰 홀더
class CourseTagListViewHolder(
    binding: HolderCourseTagListBinding,
    itemClick: (String) -> Unit,
) : BaseViewHolder<HolderCourseTagListBinding, String>(
    binding = binding,
    itemClick = itemClick
) {
    override fun display(item: String) {
        with(binding) {
            holder = this@CourseTagListViewHolder
            data = item
            executePendingBindings()
        }
    }
}