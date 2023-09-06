package com.example.eliceproject.util

import android.util.Log
import com.example.eliceproject.BuildConfig

object PrintLog {
    private const val defaultTag: String = "Elice_Project"

    /**
     * 디버그 로그 출력
     *
     * @param title 로그 타이틀
     * @param log 로그 내용
     * @param tag 로그 태그
     */
    fun d(title: String, log: Any?, tag: String = "test") {
        if (BuildConfig.DEBUG)
            Log.d("$defaultTag/$tag", "$title => $log")
    }

    /**
     * 에러 로그 출력
     *
     * @param title 로그 타이틀
     * @param log 로그 내용
     * @param tag 로그 태그
     */
    fun e(title: String, log: Any?, tag: String = "test") {
        if (BuildConfig.DEBUG)
            Log.e("$defaultTag/$tag", "$title => $log")
    }

    /**
     * 인포 로그 출력
     *
     * @param title 로그 타이틀
     * @param log 로그 내용
     * @param tag 로그 태그
     */
    fun i(title: String, log: Any?, tag: String = "test") {
        if (BuildConfig.DEBUG)
            Log.i("$defaultTag/$tag", "$title => $log")
    }
}
