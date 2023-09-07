package com.example.eliceproject.view.fragment.home

import com.example.eliceproject.R
import com.example.eliceproject.databinding.FragmentHomeBinding
import com.example.eliceproject.extention.navigate
import com.example.eliceproject.util.Navigator
import com.example.eliceproject.util.PrintLog
import com.example.eliceproject.view.fragment.BaseFragment
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.qualifier.named

class HomeFragment :
    BaseFragment<FragmentHomeBinding, Navigator.Home>(resId = R.layout.fragment_home) {

    override val fragmentBundleData: Navigator.Home =
        Navigator.Home

    private val viewModel: HomeViewModel by viewModel(qualifier = named("homeViewModel"))

    override fun onSetupUI() {
        with(binding) {
            lifecycleOwner = this@HomeFragment.viewLifecycleOwner
            view = this@HomeFragment
        }

        viewModel.lifecycleOwner = this@HomeFragment.viewLifecycleOwner

        viewModel.test()
        viewModel.test()
        viewModel.test()
        viewModel.test()
        viewModel.test()
        viewModel.test()

        viewModel.test2()
        viewModel.test2()
        viewModel.test2()
        viewModel.test2()
        viewModel.test2()
        viewModel.test2()
        viewModel.test2()
    }

    override fun observeViewModel() {
        with(viewModel) {
            testLiveData.observe {
                PrintLog.d("testLiveData", it, fragmentTag)
            }
        }
    }

    fun runCourseDetail(courseId: Int) {
        navigate(nav = Navigator.CourseDetail(courseId = courseId))
    }
}
