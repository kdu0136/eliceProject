package com.example.eliceproject.view.fragment.course_detail

import android.view.View
import androidx.recyclerview.widget.SimpleItemAnimator
import com.example.eliceproject.R
import com.example.eliceproject.databinding.FragmentCourseDetailBinding
import com.example.eliceproject.extention.getBundleData
import com.example.eliceproject.util.CustomItemDecoration
import com.example.eliceproject.util.Navigator
import com.example.eliceproject.view.fragment.BaseFragment
import com.example.eliceproject.view.fragment.home.components.adapter.CourseListAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf
import org.koin.core.qualifier.named

class CourseDetailFragment :
    BaseFragment<FragmentCourseDetailBinding, Navigator.CourseDetail>(resId = R.layout.fragment_course_detail) {

    override val fragmentBundleData: Navigator.CourseDetail by lazy {
        this.getBundleData() ?: throw NullPointerException("data is null")
    }

    private val viewModel: CourseDetailViewModel by viewModel(qualifier = named("courseDetailViewModel")) {
        parametersOf(fragmentBundleData.courseId)
    }

    private val freeCourseAdapter: CourseListAdapter by lazy {
        CourseListAdapter(itemClick = {})
    }

    override fun onSetupUI() {
        with(binding) {
            lifecycleOwner = this@CourseDetailFragment.viewLifecycleOwner
            activity = this@CourseDetailFragment.activity
            view = this@CourseDetailFragment
            viewModel = this@CourseDetailFragment.viewModel.apply {
                lifecycleOwner = this@CourseDetailFragment.viewLifecycleOwner
            }
        }

        with(binding.recyclerView) {
            adapter = this@CourseDetailFragment.freeCourseAdapter
            (itemAnimator as? SimpleItemAnimator)?.supportsChangeAnimations = false
            addItemDecoration(CustomItemDecoration(bottom = 16f))
        }
    }

    override fun observeViewModel() {
        with(viewModel) {
            testLiveData.observe {
                this@CourseDetailFragment.freeCourseAdapter.submitData(lifecycle, it)
            }

            courseDetailLiveData.observe {
                binding.withImageTitleLayout.root.visibility =
                    if (it.existImage) View.VISIBLE else View.GONE

                binding.withoutImageTitleLayout.root.visibility =
                    if (it.existImage) View.GONE else View.VISIBLE
            }
        }
    }
}
