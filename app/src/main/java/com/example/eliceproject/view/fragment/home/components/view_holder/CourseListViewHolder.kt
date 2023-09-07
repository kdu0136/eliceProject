package com.example.eliceproject.view.fragment.home.components.view_holder

import androidx.recyclerview.widget.SimpleItemAnimator
import com.example.eliceproject.data.course.model.Course
import com.example.eliceproject.databinding.HolderCourseListBinding
import com.example.eliceproject.util.CustomItemDecoration
import com.example.eliceproject.view.fragment.home.components.adapter.CourseTagListAdapter
import com.example.eliceproject.view.global_components.view_holder.BaseViewHolder
import com.google.android.flexbox.FlexDirection
import com.google.android.flexbox.FlexboxLayoutManager
import com.google.android.flexbox.JustifyContent

// 과목 리스트 뷰 홀더
class CourseListViewHolder(
    binding: HolderCourseListBinding,
    private val itemClick: (Course) -> Unit,
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

        // TODO: tag list 의 max line 이 2 인지? tag list item 의 text max line 이 2 인지?
        with(binding.tagRecyclerView) {
            val listAdapter = CourseTagListAdapter(itemClick = { itemClick(item) }).apply {
                val layoutManager = layoutManager as? FlexboxLayoutManager ?: return@apply
                layoutManager.flexDirection = FlexDirection.ROW
                layoutManager.justifyContent = JustifyContent.FLEX_START
            }.also {
                it.submitList(item.tagList ?: listOf())
            }
            adapter = listAdapter
            (itemAnimator as? SimpleItemAnimator)?.supportsChangeAnimations = false
            if (itemDecorationCount == 0) { // view holder 재사용시 추가 x
                addItemDecoration(CustomItemDecoration(right = 4f, bottom = 4f))
            }
        }
    }
}