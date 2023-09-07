package com.example.eliceproject.di

import com.example.eliceproject.view.fragment.home.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

val viewModelModules = module {
    viewModel(qualifier = named("homeViewModel")) {
        HomeViewModel()
    }
}