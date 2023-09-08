package com.example.eliceproject.view.global_components.adapter

import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.eliceproject.view.global_components.view_holder.ViewHolderType

abstract class BasePagingAdapter<T : Any, VH : RecyclerView.ViewHolder>(diffCallback: DiffUtil.ItemCallback<T>) :
    PagingDataAdapter<T, VH>(diffCallback) {
    final override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH =
        when (enumValues<ViewHolderType>().find { it.value == viewType }) {
            ViewHolderType.HEADER -> createHeaderViewHolder(parent = parent, viewType = viewType)
            else -> createBodyViewHolder(parent = parent, viewType = viewType)
        }

    abstract fun createHeaderViewHolder(parent: ViewGroup, viewType: Int): VH
    abstract fun createBodyViewHolder(parent: ViewGroup, viewType: Int): VH
}
