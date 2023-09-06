package com.example.eliceproject.view.global_components.view_holder

import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

abstract class BaseViewHolder<VB : ViewDataBinding, T>(
    protected val binding: VB,
    private val itemClick: ((T) -> Unit) = {},
) : RecyclerView.ViewHolder(binding.root) {
    abstract fun display(item: T)
    open fun onItemClick(data: T) = this@BaseViewHolder.itemClick(data)
}