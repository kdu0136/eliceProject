package com.example.eliceproject.view.fragment.course_detail

import androidx.lifecycle.*
import androidx.lifecycle.map
import androidx.paging.*
import com.example.eliceproject.data.course.CourseRepository
import com.example.eliceproject.data.course.model.Course
import com.example.eliceproject.data.lecture.LectureRepository
import com.example.eliceproject.data.lecture.model.Lecture
import com.example.eliceproject.util.PrintLog
import com.example.eliceproject.view.BaseViewModel
import com.example.eliceproject.view.global_components.view_holder.ViewHolderType

class CourseDetailViewModel(
    courseRepository: CourseRepository,
    lectureRepository: LectureRepository,
    private val courseId: Int,
) : BaseViewModel() {

    // region 수강신청 유무
    private val _isRegisterCourseLiveData: MutableLiveData<Boolean> = MutableLiveData(false)
    val isRegisterCourseLiveData: LiveData<Boolean> =
        _isRegisterCourseLiveData.distinctUntilChanged()

    fun changeRegisterState() = launch(
        jobName = object {}.javaClass.enclosingMethod?.name ?: "",
        childJob = {
            val currentState = _isRegisterCourseLiveData.value ?: return@launch
            _isRegisterCourseLiveData.postValue(!currentState)
        },
    )
    // endregion

    // region 과목 상세
    val courseDetailLiveData: LiveData<Course> = liveData {
        try {
            val data = courseRepository.getCourseDetail(courseId = courseId)
            emit(data)
        } catch (e: Exception) {
            PrintLog.e("fail getCourseDetail", e)
        }
    }
    // endregion

    // region 수업 list
    val lectureListLiveData: LiveData<PagingData<Lecture>> =
        lectureRepository.getLectureList(courseId = courseId)
            .liveData.cachedIn(viewModelScope)
    // endregion
}