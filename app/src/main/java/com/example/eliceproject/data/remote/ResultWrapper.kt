package com.example.eliceproject.data.remote

sealed class ResultWrapper<out Success> {
    data class Success<out Success>(val result: Success) : ResultWrapper<Success>()
    data class Error(val error: Throwable) : ResultWrapper<Nothing>()

    companion object {
        // status 값이 ok 일 경우만 Success
        fun <T> statusHandle(status: ResResult, result: T): ResultWrapper<T> =
            if (status.status == "ok") Success(result = result)
            else Error(error = Throwable(status.reason))
    }
}
