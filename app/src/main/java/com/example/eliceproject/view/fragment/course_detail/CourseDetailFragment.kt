package com.example.eliceproject.view.fragment.course_detail

import androidx.recyclerview.widget.ConcatAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.SimpleItemAnimator
import com.example.eliceproject.R
import com.example.eliceproject.databinding.FragmentCourseDetailBinding
import com.example.eliceproject.extention.getBundleData
import com.example.eliceproject.util.Navigator
import com.example.eliceproject.view.fragment.BaseFragment
import com.example.eliceproject.view.fragment.course_detail.components.adapter.CourseDetailHeaderAdapter
import com.example.eliceproject.view.fragment.course_detail.components.adapter.LectureListAdapter
import com.example.eliceproject.view.fragment.event.CourseRegisterUpdateEvent
import org.greenrobot.eventbus.EventBus
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

    // region adapter
    private val headerAdapter: CourseDetailHeaderAdapter =
        CourseDetailHeaderAdapter().apply {
            registerAdapterDataObserver(object : RecyclerView.AdapterDataObserver() {
                override fun onItemRangeInserted(positionStart: Int, itemCount: Int) {
                    // 과목 상세 header insert 시 scroll 처음으로
                    binding.recyclerView.smoothScrollToPosition(0)
                }
            })
        }
    private val lectureAdapter: LectureListAdapter =
        LectureListAdapter()
    private val concatAdapter: ConcatAdapter = ConcatAdapter(
        headerAdapter,
        lectureAdapter,
    )
    // endregion

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
            adapter = this@CourseDetailFragment.concatAdapter
            (itemAnimator as? SimpleItemAnimator)?.supportsChangeAnimations = false
        }

        viewModel.loadRegisteredCourse()
    }

    override fun observeViewModel() {
        with(viewModel) {
            courseDetailHeaderLiveData.observe(this@CourseDetailFragment.headerAdapter::submitList)

            lectureListLiveData.observe {
                this@CourseDetailFragment.lectureAdapter.submitData(lifecycle, it)
            }
        }
    }

    override fun onDestroy() {
        // 과목 수강 상태 변경한적 있으면 event 발송
        if (viewModel.isUpdateRegister) {
            EventBus.getDefault().post(CourseRegisterUpdateEvent)
        }
        super.onDestroy()
    }
}
