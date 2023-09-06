package com.example.eliceproject.view.fragment.course_detail

import com.example.eliceproject.R
import com.example.eliceproject.databinding.FragmentCourseDetailBinding
import com.example.eliceproject.extention.getBundleData
import com.example.eliceproject.util.Navigator
import com.example.eliceproject.view.fragment.BaseFragment

class CourseDetailFragment :
    BaseFragment<FragmentCourseDetailBinding, Navigator.CourseDetail>(resId = R.layout.fragment_course_detail) {

    override val fragmentBundleData: Navigator.CourseDetail by lazy {
        this.getBundleData() ?: throw NullPointerException("data is null")
    }

    override fun onSetupUI() {
        with(binding) {
            lifecycleOwner = this@CourseDetailFragment.viewLifecycleOwner
        }

        binding.text1.text = fragmentBundleData.courseId.toString()
    }

    override fun observeViewModel() {
    }
}
