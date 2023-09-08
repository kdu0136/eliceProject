package com.example.eliceproject.di

import com.example.eliceproject.data.course.CourseRepository
import com.example.eliceproject.data.course.CourseRepositoryImpl
import com.example.eliceproject.data.lecture.LectureRepository
import com.example.eliceproject.data.lecture.LectureRepositoryImpl
import com.example.eliceproject.remote.ApiURL
import com.example.eliceproject.remote.Http
import com.example.eliceproject.remote.interfaces.CourseRequest
import com.example.eliceproject.remote.interfaces.LectureRequest
import com.example.eliceproject.remote.service.ApiCourseService
import com.example.eliceproject.remote.service.ApiCourseServiceImpl
import com.example.eliceproject.remote.service.ApiLectureService
import com.example.eliceproject.remote.service.ApiLectureServiceImpl
import com.example.eliceproject.view.fragment.course_detail.CourseDetailViewModel
import com.example.eliceproject.view.fragment.home.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

val remoteModules = module {
    single(qualifier = named("httpClient")) {
        Http.createOkHttpClient()
    }

    // region course api
    single(qualifier = named("courseRequest")) {
        Http.createRetrofit<CourseRequest>(
            baseUrl = ApiURL.BASE_URL,
            client = get(qualifier = named("httpClient")),
        )
    }
    single<ApiCourseService>(qualifier = named("apiCourseService")) {
        ApiCourseServiceImpl(
            courseRequest = get(qualifier = named("courseRequest"))
        )
    }
    // endregion

    // region lecture api
    single(qualifier = named("lectureRequest")) {
        Http.createRetrofit<LectureRequest>(
            baseUrl = ApiURL.BASE_URL,
            client = get(qualifier = named("httpClient")),
        )
    }
    single<ApiLectureService>(qualifier = named("apiLectureService")) {
        ApiLectureServiceImpl(
            lectureRequest = get(qualifier = named("lectureRequest"))
        )
    }
    // endregion
}

val repositoryModules = module {
    single<CourseRepository>(qualifier = named("courseRepository")) {
        CourseRepositoryImpl()
    }

    single<LectureRepository>(qualifier = named("lectureRepository")) {
        LectureRepositoryImpl()
    }
}

val viewModelModules = module {
    viewModel(qualifier = named("homeViewModel")) {
        HomeViewModel(
            courseRepository = get(qualifier = named("courseRepository")),
        )
    }

    viewModel(qualifier = named("courseDetailViewModel")) {(courseId: Int) ->
        CourseDetailViewModel(
            courseRepository = get(qualifier = named("courseRepository")),
            lectureRepository = get(qualifier = named("lectureRepository")),
            courseId = courseId,
        )
    }
}