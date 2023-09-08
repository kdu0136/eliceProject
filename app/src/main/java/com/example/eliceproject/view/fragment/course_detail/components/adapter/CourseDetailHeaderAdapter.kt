package com.example.eliceproject.view.fragment.course_detail.components.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.eliceproject.R
import com.example.eliceproject.data.course.model.CourseDetailHeader
import com.example.eliceproject.data.course.model.CourseDetailHeaderType
import com.example.eliceproject.util.createDataBinding
import com.example.eliceproject.view.fragment.course_detail.components.view_holder.CourseDetailHeaderDescriptionViewHolder
import com.example.eliceproject.view.fragment.course_detail.components.view_holder.CourseDetailHeaderTitleWithBannerViewHolder
import com.example.eliceproject.view.fragment.course_detail.components.view_holder.CourseDetailHeaderTitleWithoutViewHolder
import com.example.eliceproject.view.global_components.adapter.BaseListAdapter

// 과목 상세 헤더(title, description) adapter
class CourseDetailHeaderAdapter :
    BaseListAdapter<CourseDetailHeader, RecyclerView.ViewHolder>(CourseDetailHeader.DiffCallback) {

    override fun getItemViewType(position: Int): Int =
        when (getItem(position)) {
            is CourseDetailHeader.TitleWithBanner -> CourseDetailHeaderType.TITLE_WITH_BANNER
            is CourseDetailHeader.TitleWithoutBanner -> CourseDetailHeaderType.TITLE_WITHOUT_BANNER
            is CourseDetailHeader.Description -> CourseDetailHeaderType.DESCRIPTION
        }.value

    override fun createBodyViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        when (enumValues<CourseDetailHeaderType>().find { it.value == viewType }
            ?: CourseDetailHeaderType.DESCRIPTION) {
            CourseDetailHeaderType.TITLE_WITH_BANNER -> CourseDetailHeaderTitleWithBannerViewHolder(
                createDataBinding(
                    resId = R.layout.holder_course_detail_header_title_with_banner,
                    context = parent.context,
                    parent = parent,
                ),
            )
            CourseDetailHeaderType.TITLE_WITHOUT_BANNER -> CourseDetailHeaderTitleWithoutViewHolder(
                binding = createDataBinding(
                    resId = R.layout.holder_course_detail_header_title_without_banner,
                    context = parent.context,
                    parent = parent,
                ),
            )
            CourseDetailHeaderType.DESCRIPTION -> CourseDetailHeaderDescriptionViewHolder(
                binding = createDataBinding(
                    resId = R.layout.holder_course_detail_header_description,
                    context = parent.context,
                    parent = parent,
                ),
            )
        }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = getItem(position) ?: return

        if (holder is CourseDetailHeaderTitleWithBannerViewHolder &&
            item is CourseDetailHeader.TitleWithBanner
        ) holder.display(item)
        else if (holder is CourseDetailHeaderTitleWithoutViewHolder &&
            item is CourseDetailHeader.TitleWithoutBanner
        ) holder.display(item)
        else if (holder is CourseDetailHeaderDescriptionViewHolder &&
            item is CourseDetailHeader.Description
        ) holder.display(item)
    }
}