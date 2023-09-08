package com.example.eliceproject.view.fragment.course_detail.components.view_holder

import com.example.eliceproject.data.course.model.CourseDetailHeader
import com.example.eliceproject.databinding.HolderCourseDetailHeaderTitleWithBannerBinding
import com.example.eliceproject.view.global_components.view_holder.BaseViewHolder

// 과목 상세 헤더 타이틀(banner o) 뷰 홀더
class CourseDetailHeaderTitleWithBannerViewHolder(
    binding: HolderCourseDetailHeaderTitleWithBannerBinding,
) : BaseViewHolder<HolderCourseDetailHeaderTitleWithBannerBinding, CourseDetailHeader.TitleWithBanner>(
    binding = binding,
) {
    override fun display(item: CourseDetailHeader.TitleWithBanner) {
        with(binding) {
            data = item
            executePendingBindings()
        }
    }
}