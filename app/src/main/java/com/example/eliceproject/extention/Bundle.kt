package com.example.eliceproject.extention

import android.os.Build
import android.os.Bundle
import java.io.Serializable

// key 에 해당하는 serializable value 반환
@Suppress("DEPRECATION")
inline fun <reified T : Serializable> Bundle.customGetSerializable(key: String): T? {
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
        getSerializable(key, T::class.java)
    } else {
        getSerializable(key) as? T
    }
}
