package com.example.eliceproject.view.fragment.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.distinctUntilChanged
import com.example.eliceproject.util.PrintLog
import com.example.eliceproject.view.BaseViewModel
import kotlinx.coroutines.delay

class HomeViewModel(
) : BaseViewModel() {
    private val _testLiveData: MutableLiveData<String> = MutableLiveData("hello")
    val testLiveData: LiveData<String> = _testLiveData.distinctUntilChanged()

    fun test() = launch(
        childJob = {
            PrintLog.d("func", "test")
        },
        endJob = {},
        exception = {},
    )

    fun test2() = launch(
        jobName = object {}.javaClass.enclosingMethod?.name ?: "",
        childJob = {
            PrintLog.d("func", "test2")
            delay(1000)
        },
        endJob = {},
        exception = {},
    )
}