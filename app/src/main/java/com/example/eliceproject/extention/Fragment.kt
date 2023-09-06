package com.example.eliceproject.extention

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.eliceproject.util.Navigator
import com.example.eliceproject.util.PrintLog
import java.io.Serializable

// Navigator 에 맞는 fragment 실행
fun Fragment.navigate(nav: Navigator) {
    val navId = nav.getNavId()

    // fragment 이동
    PrintLog.d("fragment navigate", "data: $nav", "Navigator")
    val bundle = Bundle().apply {
        putSerializable("fragmentBundleData", nav)
    }
    findNavController().navigate(resId = navId, args = bundle)
}

// fragment 의 bundle data 반환
inline fun <reified T: Serializable> Fragment.getBundleData(): T? =
    arguments?.customGetSerializable("fragmentBundleData")
