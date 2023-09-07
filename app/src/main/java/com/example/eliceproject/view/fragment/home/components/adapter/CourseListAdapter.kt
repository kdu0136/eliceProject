package com.example.eliceproject.view.fragment.home.components.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.eliceproject.R
import com.example.eliceproject.data.course.model.Course
import com.example.eliceproject.util.createDataBinding
import com.example.eliceproject.view.fragment.home.components.view_holder.CourseListViewHolder
import com.example.eliceproject.view.global_components.adapter.BasePagingAdapter

// 과목 리스트 뷰 adapter
class CourseListAdapter(
    private val itemClick: (Course) -> Unit,
) : BasePagingAdapter<Course, RecyclerView.ViewHolder>(Course.DiffCallback) {
    override fun createBodyViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        CourseListViewHolder(
            binding = createDataBinding(
                resId = R.layout.holder_course_list,
                context = parent.context,
                parent = parent,
            ),
            itemClick = itemClick,
        )

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = getItem(position) ?: return
        when (holder) {
            is CourseListViewHolder -> holder.display(item)
        }
    }
}