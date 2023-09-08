package com.example.eliceproject.view.fragment.course_detail.components.view_holder

import com.example.eliceproject.data.course.model.Lecture
import com.example.eliceproject.databinding.HolderLectureListBinding
import com.example.eliceproject.view.global_components.view_holder.BaseViewHolder

// 수업 리스트 뷰 홀더
class LectureListViewHolder(
    binding: HolderLectureListBinding,
) : BaseViewHolder<HolderLectureListBinding, Lecture>(
    binding = binding,
) {
    override fun display(item: Lecture) {
        with(binding) {
            data = item
            executePendingBindings()
        }
    }
}