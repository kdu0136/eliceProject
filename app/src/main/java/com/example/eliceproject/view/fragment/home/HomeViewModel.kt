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
    val testLiveData: LiveData<PagingData<Course>> =
        courseRepository.getCourseList(type = CourseType.FREE)
            .liveData.cachedIn(viewModelScope)
}