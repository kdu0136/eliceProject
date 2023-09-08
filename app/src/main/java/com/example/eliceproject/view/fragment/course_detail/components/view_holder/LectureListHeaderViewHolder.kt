package com.example.eliceproject.view.fragment.course_detail.components.view_holder

import com.example.eliceproject.databinding.HolderLectureListHeaderBinding
import com.example.eliceproject.view.global_components.view_holder.BaseViewHolder

// 수업 리스트 헤더 뷰 홀더
class LectureListHeaderViewHolder(
    binding: HolderLectureListHeaderBinding,
) : BaseViewHolder<HolderLectureListHeaderBinding, Unit>(
    binding = binding,
) {
    override fun display(item: Unit) {
        with(binding) {
            executePendingBindings()
        }
    }
}