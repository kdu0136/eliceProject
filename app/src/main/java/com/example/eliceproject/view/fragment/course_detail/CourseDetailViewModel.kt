package com.example.eliceproject.view.fragment.course_detail

import androidx.lifecycle.*
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.liveData
import com.example.eliceproject.data.course.CourseRepository
import com.example.eliceproject.data.course.model.Course
import com.example.eliceproject.data.course.model.CourseDetailHeader
import com.example.eliceproject.data.lecture.LectureRepository
import com.example.eliceproject.data.lecture.model.Lecture
import com.example.eliceproject.util.PrintLog
import com.example.eliceproject.view.BaseViewModel

class CourseDetailViewModel(
    private val courseRepository: CourseRepository,
    lectureRepository: LectureRepository,
    private val courseId: Int,
) : BaseViewModel() {

    // region 수강신청 유무
    var isUpdateRegister: Boolean = false // 수강신청 변경 여부

    private var isRegisterCourse: Boolean = false
        set(value) {
            field = value
            _isRegisterCourseLiveData.postValue(field)
        }
    private val _isRegisterCourseLiveData: MutableLiveData<Boolean> = MutableLiveData()
    val isRegisterCourseLiveData: LiveData<Boolean> =
        _isRegisterCourseLiveData.distinctUntilChanged()

    // 해당 course 수강 유무 확인
    fun loadRegisteredCourse() = launch(
        childJob = {
            isRegisterCourse = courseRepository.getIsExistCourse(courseId = courseId)
        },
    )

    // 해당 course 수강 유무 변경
    fun changeRegisterState() = launch(
        jobName = object {}.javaClass.enclosingMethod?.name ?: "",
        childJob = {
            isRegisterCourse = courseRepository.updateMyCourse(courseId = courseId)
            isUpdateRegister = true
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

    // course detail 정보로 header adapter data 변경
    val courseDetailHeaderLiveData: LiveData<List<CourseDetailHeader>> =
        courseDetailLiveData.map {
            parseToCourseHeaderData(course = it)
        }
    private fun parseToCourseHeaderData(course: Course): List<CourseDetailHeader> {
        val headerDataList: ArrayList<CourseDetailHeader> = ArrayList()

        val existBanner = course.bannerUrl?.isNotEmpty() ?: false
        val titleData =
            if (existBanner) {
                CourseDetailHeader.TitleWithBanner(
                    title = course.title ?: "",
                    logoUrl = course.logoUrl ?: "",
                    bannerUrl = course.bannerUrl ?: "",
                )
            } else {
                CourseDetailHeader.TitleWithoutBanner(
                    title = course.title ?: "",
                    logoUrl = course.logoUrl ?: "",
                    bannerUrl = course.bannerUrl ?: "",
                    shortDescription = course.shortDescription ?: "",
                )
            }
        headerDataList.add(titleData)

        // 과목 소개가 있을 경우만 추가
        if (course.description != null && course.description.isNotEmpty()) {
            headerDataList.add(
                CourseDetailHeader.Description(
                    description = course.description ?: "",
                )
            )
        }

        return headerDataList
    }
    // endregion

    // region 수업 list
    val lectureListLiveData: LiveData<PagingData<Lecture>> =
        lectureRepository.getLectureList(courseId = courseId)
            .liveData.cachedIn(viewModelScope)
    // endregion
}