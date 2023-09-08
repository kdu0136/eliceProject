package com.example.eliceproject.view.fragment.course_detail.components.view_holder

import com.example.eliceproject.data.course.model.CourseDetailHeader
import com.example.eliceproject.databinding.HolderCourseDetailHeaderTitleWithoutBannerBinding
import com.example.eliceproject.view.global_components.view_holder.BaseViewHolder

// 과목 상세 헤더 타이틀(banner x) 뷰 홀더
class CourseDetailHeaderTitleWithoutViewHolder(
    binding: HolderCourseDetailHeaderTitleWithoutBannerBinding,
) : BaseViewHolder<HolderCourseDetailHeaderTitleWithoutBannerBinding, CourseDetailHeader.TitleWithoutBanner>(
    binding = binding,
) {
    override fun display(item: CourseDetailHeader.TitleWithoutBanner) {
        with(binding) {
            data = item
            executePendingBindings()
        }
    }
}