package com.example.eliceproject.view.fragment.home

import com.example.eliceproject.R
import com.example.eliceproject.databinding.FragmentHomeBinding
import com.example.eliceproject.extention.navigate
import com.example.eliceproject.util.Navigator
import com.example.eliceproject.view.fragment.BaseFragment

class HomeFragment :
    BaseFragment<FragmentHomeBinding, Navigator.Home>(resId = R.layout.fragment_home) {

    override val fragmentBundleData: Navigator.Home =
        Navigator.Home

    override fun onSetupUI() {
        with(binding) {
            lifecycleOwner = this@HomeFragment.viewLifecycleOwner
            view = this@HomeFragment
        }
    }

    override fun observeViewModel() {
    }

    fun runCourseDetail(courseId: Int) {
        navigate(nav = Navigator.CourseDetail(courseId = courseId))
    }
}
