package com.example.eliceproject.data

data class PagingSourceData<Value : Any>(
    val data: List<Value> = listOf(),
    var prevPage: Int? = null,
    var nextPage: Int? = null,
)
