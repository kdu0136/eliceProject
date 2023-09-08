package com.example.eliceproject.view.fragment.home.components.view_holder

import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.SimpleItemAnimator
import com.example.eliceproject.data.course.model.Course
import com.example.eliceproject.databinding.HolderCourseListBinding
import com.example.eliceproject.extention.dipToPx
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

        // banner image 유무에 따른 view visible 설정
        if (item.bannerUrl == null || item.bannerUrl.isEmpty()) {
            binding.logoImage.visibility = View.VISIBLE
            binding.bannerImage.visibility = View.GONE
        } else {
            binding.logoImage.visibility = View.GONE
            binding.bannerImage.visibility = View.VISIBLE
        }

        with(binding.tagRecyclerView) {
            val listAdapter = CourseTagListAdapter(itemClick = { itemClick(item) }).apply {
                val layoutManager = layoutManager as? FlexboxLayoutManager ?: return@apply
                layoutManager.flexDirection = FlexDirection.ROW
                layoutManager.justifyContent = JustifyContent.FLEX_START
            }
            listAdapter.registerAdapterDataObserver(object : RecyclerView.AdapterDataObserver() {
                override fun onItemRangeInserted(positionStart: Int, itemCount: Int) {
                    // tag view holder 가 추가된 이후 holder 의 크기를 측정하여 recyclerView 의 height 값 설정 (visible max line 2 가 되도록)
                    if (itemCount > 0) {
                        binding.tagRecyclerView.post {
                            val layoutManager =
                                layoutManager as? FlexboxLayoutManager ?: return@post
                            val viewHeight =
                                layoutManager.findViewByPosition(0)?.measuredHeight ?: return@post

                            val lp = this@with.layoutParams as? ConstraintLayout.LayoutParams
                            if (lp != null) {
                                // view holder 2개 크기 + recyclerView decoration bottom 크기
                                lp.height = 4f.dipToPx.toInt() + viewHeight * 2
                            }
                        }
                    }
                }
            })
            adapter = listAdapter
            (itemAnimator as? SimpleItemAnimator)?.supportsChangeAnimations = false
            if (itemDecorationCount == 0) { // view holder 재사용시 추가 x
                addItemDecoration(CustomItemDecoration(right = 4f, bottom = 4f))
            }

            listAdapter.submitList(item.tagList ?: listOf())
        }
    }
}