package com.example.eliceproject.view.fragment.home

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
import com.example.eliceproject.view.fragment.home.components.adapter.CourseListAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.qualifier.named

class HomeFragment :
    BaseFragment<FragmentHomeBinding, Navigator.Home>(resId = R.layout.fragment_home) {

    override val fragmentBundleData: Navigator.Home =
        Navigator.Home

    private val viewModel: HomeViewModel by viewModel(qualifier = named("homeViewModel"))

    // region adapter
    private val freeCourseAdapter: CourseListAdapter by lazy {
        CourseListAdapter(itemClick = {
            PrintLog.d("course", it)
        })
    }
    private val recommendCourseAdapter: CourseListAdapter by lazy {
        CourseListAdapter(itemClick = this::runCourseDetail)
    }
    private val myCourseAdapter: CourseListAdapter by lazy {
        CourseListAdapter(itemClick = this::runCourseDetail)
    }
    // endregion

    override fun onSetupUI() {
        with(binding) {
            lifecycleOwner = this@HomeFragment.viewLifecycleOwner
            view = this@HomeFragment
        }
        with(binding.courseFreeLayout) {
            lifecycleOwner = this@HomeFragment.viewLifecycleOwner
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
