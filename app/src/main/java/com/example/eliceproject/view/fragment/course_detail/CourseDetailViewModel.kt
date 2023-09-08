package com.example.eliceproject.view.fragment.course_detail

import androidx.lifecycle.*
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.insertHeaderItem
import androidx.paging.liveData
import com.example.eliceproject.data.course.CourseRepository
import com.example.eliceproject.data.course.model.Course
import com.example.eliceproject.data.lecture.LectureRepository
import com.example.eliceproject.data.lecture.model.Lecture
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

        }
    }
    // endregion

    // region 수업 list
    val lectureListLiveData: LiveData<PagingData<Lecture>> =
        lectureRepository.getLectureList()
            .liveData.cachedIn(viewModelScope).map {
                it.insertHeaderItem(item = Lecture.emptyData().apply {
                    type = ViewHolderType.HEADER
                })
            }
    // endregion
}