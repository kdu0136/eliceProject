package com.example.eliceproject.extention

import android.content.res.Resources
import android.util.TypedValue

val Float.dipToPx: Float
    get() = TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_DIP,
        this,
        Resources.getSystem().displayMetrics
    )
