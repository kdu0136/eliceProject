package com.example.eliceproject.data

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.eliceproject.util.PrintLog

abstract class BasePagingSource<Value : Any>(
    private val initPage: Int = 1,
) : PagingSource<Int, Value>() {
    protected val tag: String = javaClass.simpleName

    final override fun getRefreshKey(state: PagingState<Int, Value>): Int? {
        val refreshKey = state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }

        PrintLog.d("getRefreshKey", "$refreshKey", tag)
        return refreshKey
    }

    final override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Value> =
        try {
            // key(page) 가 없는 경우 초기 page
            val page = params.key ?: let {
                PrintLog.d("params.key null", "initPage: $initPage", tag)
                initPage
            }
            PrintLog.d("load", "page: $page, count: ${params.loadSize}", tag)
            val data: PagingSourceData<Value> = getNewData(page = page, count = params.loadSize)

            LoadResult.Page(
                data = data.data,
                prevKey = data.prevPage,
                nextKey = data.nextPage,
            )
        } catch (e: Exception) {
            PrintLog.e("Exception", e, tag)
            LoadResult.Error(e)
        }

    protected abstract suspend fun getNewData(page: Int, count: Int): PagingSourceData<Value>
}
