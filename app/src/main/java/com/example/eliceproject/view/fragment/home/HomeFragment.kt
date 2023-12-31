package com.example.eliceproject.view.fragment.home

import android.content.Context
import android.os.Bundle
import android.os.Parcelable
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.SimpleItemAnimator
import com.example.eliceproject.R
import com.example.eliceproject.data.course.model.Course
import com.example.eliceproject.databinding.FragmentHomeBinding
import com.example.eliceproject.extention.customGetParcelable
import com.example.eliceproject.extention.navigate
import com.example.eliceproject.util.CustomItemDecoration
import com.example.eliceproject.util.Navigator
import com.example.eliceproject.util.PrintLog
import com.example.eliceproject.view.fragment.BaseFragment
import com.example.eliceproject.view.fragment.event.CourseRegisterUpdateEvent
import com.example.eliceproject.view.fragment.home.components.adapter.CourseListAdapter
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.qualifier.named

class HomeFragment :
    BaseFragment<FragmentHomeBinding, Navigator.Home>(resId = R.layout.fragment_home) {

    override val fragmentBundleData: Navigator.Home =
        Navigator.Home

    private val viewModel: HomeViewModel by viewModel(qualifier = named("homeViewModel"))

    // region adapter
    private val freeCourseAdapter: CourseListAdapter by lazy {
        CourseListAdapter(itemClick = this::runCourseDetail)
    }
    private val recommendCourseAdapter: CourseListAdapter by lazy {
        CourseListAdapter(itemClick = this::runCourseDetail)
    }
    private val myCourseAdapter: CourseListAdapter by lazy {
        CourseListAdapter(itemClick = this::runCourseDetail).apply {
            registerAdapterDataObserver(object : RecyclerView.AdapterDataObserver() {
                override fun onItemRangeInserted(positionStart: Int, itemCount: Int) {
                    // 과목 수강 신청시 scroll 처음으로
                    if (positionStart == 0 && itemCount == 1) {
                        binding.courseMyLayout.recyclerView.smoothScrollToPosition(0)
                    }
                }
            })
        }
    }
    // endregion

    override fun onSetupUI() {
        with(binding) {
            lifecycleOwner = this@HomeFragment.viewLifecycleOwner
            view = this@HomeFragment
        }
        viewModel.lifecycleOwner = this@HomeFragment.viewLifecycleOwner

        with(binding.courseFreeLayout.recyclerView) {
            adapter = this@HomeFragment.freeCourseAdapter
            (itemAnimator as? SimpleItemAnimator)?.supportsChangeAnimations = false
            addItemDecoration(CustomItemDecoration(right = 16f))
        }
        with(binding.courseRecommendLayout.recyclerView) {
            adapter = this@HomeFragment.recommendCourseAdapter
            (itemAnimator as? SimpleItemAnimator)?.supportsChangeAnimations = false
            addItemDecoration(CustomItemDecoration(right = 16f))
        }
        with(binding.courseMyLayout.recyclerView) {
            adapter = this@HomeFragment.myCourseAdapter
            (itemAnimator as? SimpleItemAnimator)?.supportsChangeAnimations = false
            addItemDecoration(CustomItemDecoration(right = 16f))
        }
    }

    override fun observeViewModel() {
        with(viewModel) {
            freeCourseListLiveData.observe {
                this@HomeFragment.freeCourseAdapter.submitData(lifecycle, it)
            }

            recommendCourseListLiveData.observe {
                this@HomeFragment.recommendCourseAdapter.submitData(lifecycle, it)
            }

            myCourseListLiveData.observe {
                this@HomeFragment.myCourseAdapter.submitData(lifecycle, it)
            }
        }
    }

    private var scrollViewBundle: Bundle? = null
    private var recyclerViewBundle: Bundle? = null
    override fun onPause() {
        // recyclerView state 저장
        recyclerViewBundle = Bundle().apply {
            putParcelable(
                "freeLayoutManagerState",
                binding.courseFreeLayout.recyclerView.layoutManager?.onSaveInstanceState()
            )
            putParcelable(
                "recommendLayoutManagerState",
                binding.courseRecommendLayout.recyclerView.layoutManager?.onSaveInstanceState()
            )
            putParcelable(
                "myLayoutManagerState",
                binding.courseMyLayout.recyclerView.layoutManager?.onSaveInstanceState()
            )
        }

        // scrollView position 저장
        scrollViewBundle = Bundle().apply {
            putInt(
                "scrollViewScrollY",
                binding.scrollView.scrollY
            )
        }

        super.onPause()
    }

    override fun onResume() {
        super.onResume()

        // recyclerView state 복원
        recyclerViewBundle?.run {
            restoreRecyclerViewState(
                savedStateKey = "freeLayoutManagerState",
                bundle = this,
                recyclerView = binding.courseFreeLayout.recyclerView,
            )
            restoreRecyclerViewState(
                savedStateKey = "recommendLayoutManagerState",
                bundle = this,
                recyclerView = binding.courseRecommendLayout.recyclerView,
            )
            restoreRecyclerViewState(
                savedStateKey = "myLayoutManagerState",
                bundle = this,
                recyclerView = binding.courseMyLayout.recyclerView,
            )
        }

        // scrollView position 복원
        scrollViewBundle?.run {
            binding.scrollView.post {
                val positionY = this.getInt("scrollViewScrollY")
                binding.scrollView.scrollTo(0, positionY)
            }
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        EventBus.getDefault().register(this)
    }

    override fun onDetach() {
        super.onDetach()
        EventBus.getDefault().unregister(this)
    }

    @Subscribe(threadMode = ThreadMode.POSTING)
    fun updateCourseRegister(event: CourseRegisterUpdateEvent) {
        PrintLog.d("updateCourseRegister Event", event, fragmentTag)
        myCourseAdapter.refresh()
    }

    private fun restoreRecyclerViewState(
        savedStateKey: String,
        bundle: Bundle,
        recyclerView: RecyclerView,
    ) {
        recyclerView.visibility = View.INVISIBLE
        recyclerView.post {
            recyclerView.visibility = View.VISIBLE
            bundle.customGetParcelable<Parcelable>(savedStateKey)?.run {
                recyclerView.layoutManager?.onRestoreInstanceState(this)
            }
        }
    }

    private fun runCourseDetail(course: Course) {
        navigate(nav = Navigator.CourseDetail(courseId = course.id))
    }
}
