package com.example.eliceproject.view.global_components.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

abstract class BaseListAdapter<T, VH : RecyclerView.ViewHolder>(diffCallback: DiffUtil.ItemCallback<T>) :
    ListAdapter<T, VH>(diffCallback) {
    final override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH =
        createBodyViewHolder(parent = parent, viewType = viewType)

    abstract fun createBodyViewHolder(parent: ViewGroup, viewType: Int): VH
}
