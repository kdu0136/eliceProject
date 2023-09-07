package com.example.eliceproject.view

import androidx.annotation.CallSuper
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import com.example.eliceproject.util.PrintLog
import kotlinx.coroutines.*

abstract class BaseViewModel : ViewModel() {
    protected val viewModelTag = javaClass.simpleName

    private val jobMap: HashMap<String, Boolean> =
        HashMap()// 진행중 작업 리스트 이름 (같은 이름 작업 중복 방지 위함)

    private val supervisorJob = SupervisorJob() // scope 에서 exception 으로 다른 task 영향 없기 위함
    private val scope = CoroutineScope(Dispatchers.Default + supervisorJob)

    lateinit var lifecycleOwner: LifecycleOwner

    fun <T> LiveData<T>.observe(observer: Observer<in T>) {
        if (::lifecycleOwner.isInitialized)
            this.observe(lifecycleOwner, observer)
    }

    /**
     * 중복 작업을 허용하지 않는 경우 호출
     * 진행중 작업이 아닐 경우 작업 실행
     * 반드시 작업 완료 후 jobName 을 endJob method 로 보내야함
     *
     * @param jobName 진행중 작업 map에 올릴 job name
     */
    fun launch(
        jobName: String,
        childJob: suspend CoroutineScope.() -> Unit,
        endJob: suspend CoroutineScope.() -> Unit,
        exception: (Exception) -> Unit,
    ) {
        if (jobMap[jobName] == null) {
            addJob(jobName = jobName, jobMap = jobMap)

            scope.launch {
                try {
                    printJobState(jobName = jobName, state = "start")
                    childJob()
                } catch (e: Exception) {
                    exception(e)
                    printJobState(jobName = jobName, state = "error: $e", isError = true)
                } finally {
                    endJob()
                    printJobState(jobName = jobName, state = "end")
                    removeJob(jobName = jobName, jobMap = jobMap)
                }
            }
        }
    }

    // 중복 작업해도 문제 없을 경우 호출
    fun launch(
        childJob: suspend CoroutineScope.() -> Unit,
        endJob: suspend CoroutineScope.() -> Unit,
        exception: (Exception) -> Unit,
    ) {
        scope.launch {
            try {
                childJob()
            } catch (e: Exception) {
                exception(e)
                printJobState(state = "error: $e", isError = true)
            } finally {
                endJob()
            }
        }
    }

    // 진행중 작업 리스트에서 추가
    private fun addJob(jobName: String, jobMap: HashMap<String, Boolean>) {
        printJobState(jobName = jobName, state = "add")
        jobMap[jobName] = true
    }

    // 진행중 작업 리스트에서 제거
    private fun removeJob(jobName: String, jobMap: HashMap<String, Boolean>) {
        printJobState(jobName = jobName, state = "remove")
        jobMap.remove(jobName)
    }

    private fun printJobState(jobName: String = "", state: String, isError: Boolean = false) {
        if (isError) PrintLog.e("job('$jobName')", state, viewModelTag)
        else PrintLog.d("job('$jobName')", state, viewModelTag)
    }

    // view model 이 clear 될 떄 jobMap 모두 clear
    @CallSuper
    override fun onCleared() {
        PrintLog.d("onCleared", "", viewModelTag)
        super.onCleared()
        jobMap.clear()
        scope.coroutineContext.cancelChildren()
    }
}