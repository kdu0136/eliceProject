package com.example.eliceproject.view.fragment.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.liveData
import com.example.eliceproject.data.course.CourseRepository
import com.example.eliceproject.data.course.model.Course
import com.example.eliceproject.data.course.model.CourseType
import com.example.eliceproject.view.BaseViewModel

class HomeViewModel(
    courseRepository: CourseRepository,
) : BaseViewModel() {
    // region free course
    val freeCourseListLiveData: LiveData<PagingData<Course>> =
        courseRepository.getCourseList(type = CourseType.FREE)
            .liveData.cachedIn(viewModelScope)
    // endregion

    // region recommend course
    val recommendCourseListLiveData: LiveData<PagingData<Course>> =
        courseRepository.getCourseList(type = CourseType.RECOMMEND)
            .liveData.cachedIn(viewModelScope)
    // endregion

    // region my course
    val myCourseListLiveData: LiveData<PagingData<Course>> =
        courseRepository.getMyCourseList()
            .liveData.cachedIn(viewModelScope)
    // endregion
}