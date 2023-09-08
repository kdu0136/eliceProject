package com.example.eliceproject.view.fragment.home.components.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.eliceproject.R
import com.example.eliceproject.util.DiffUtil
import com.example.eliceproject.util.createDataBinding
import com.example.eliceproject.view.fragment.home.components.view_holder.CourseTagListViewHolder
import com.example.eliceproject.view.global_components.adapter.BaseListAdapter

// 과목 태그 리스트 adapter
class CourseTagListAdapter(
    private val itemClick: (String) -> Unit,
) : BaseListAdapter<String, RecyclerView.ViewHolder>(DiffUtil.StringDiffCallback) {
    override fun createBodyViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        CourseTagListViewHolder(
            binding = createDataBinding(
                resId = R.layout.holder_course_tag_list,
                context = parent.context,
                parent = parent,
            ),
            itemClick = itemClick,
        )

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = getItem(position) ?: return
        when (holder) {
            is CourseTagListViewHolder -> holder.display(item)
        }
    }
}