package com.example.eliceproject.util

import androidx.recyclerview.widget.DiffUtil

object DiffUtil {
    val StringDiffCallback: DiffUtil.ItemCallback<String>
        get() = object : DiffUtil.ItemCallback<String>() {
            override fun areItemsTheSame(oldItem: String, newItem: String): Boolean =
                oldItem == newItem

            override fun areContentsTheSame(oldItem: String, newItem: String): Boolean =
                oldItem == newItem
        }
}