package com.example.eliceproject.di

import com.example.eliceproject.data.course.CourseRepository
import com.example.eliceproject.data.course.CourseRepositoryImpl
import com.example.eliceproject.view.fragment.course_detail.CourseDetailViewModel
import com.example.eliceproject.view.fragment.home.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

val repositoryModules = module {
    single<CourseRepository>(qualifier = named("courseRepository")) {
        CourseRepositoryImpl()
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
            courseId = courseId,
        )
    }
}