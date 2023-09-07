package com.example.eliceproject.util

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding

fun <VB : ViewDataBinding> createDataBinding(
    @LayoutRes resId: Int,
    context: Context,
    parent: ViewGroup? = null,
    attachToParent: Boolean = false,
): VB =
    DataBindingUtil.inflate(LayoutInflater.from(context), resId, parent, attachToParent)
