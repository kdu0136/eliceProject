package com.example.eliceproject.view.fragment.course_detail.components.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.eliceproject.R
import com.example.eliceproject.data.lecture.model.Lecture
import com.example.eliceproject.util.createDataBinding
import com.example.eliceproject.view.fragment.course_detail.components.view_holder.LectureListHeaderViewHolder
import com.example.eliceproject.view.fragment.course_detail.components.view_holder.LectureListViewHolder
import com.example.eliceproject.view.global_components.adapter.BasePagingAdapter
import com.example.eliceproject.view.global_components.view_holder.ViewHolderType

// 수업 리스트 adapter
class LectureListAdapter :
    BasePagingAdapter<Lecture, RecyclerView.ViewHolder>(Lecture.DiffCallback) {

    override fun getItemViewType(position: Int): Int {
        val data = getItem(position) ?: return ViewHolderType.HEADER.value
        return data.type.value
    }

    override fun createBodyViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        LectureListViewHolder(
            binding = createDataBinding(
                resId = R.layout.holder_lecture_list,
                context = parent.context,
                parent = parent,
            ),
        )

    override fun createHeaderViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        LectureListHeaderViewHolder(
            binding = createDataBinding(
                resId = R.layout.holder_lecture_list_header,
                context = parent.context,
                parent = parent,
            ),
        )

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = getItem(position) ?: return

        // 마지막 data 인지
        item.isLast = position == itemCount - 1

        when (holder) {
            is LectureListHeaderViewHolder -> holder.display(Unit)
            is LectureListViewHolder -> holder.display(item)
        }
    }
}