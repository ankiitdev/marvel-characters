package com.ankiitdev.marvelcharacters.core.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.ankiitdev.marvelcharacters.core.data.network.service.ApiService
import com.ankiitdev.marvelcharacters.core.models.CharacterEntity

// FETCHES LIST WITHOUT CACHING
class CharactersPagingSource(
    private val api: ApiService
) : PagingSource<Int, CharacterEntity>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, CharacterEntity> {
        val currentPage = params.key ?: 1
        return try {
            val response = api.getCharacters().data.results
            val endOfPaginationReached = true
            LoadResult.Page(
                data = response,
                prevKey = if (currentPage == 1) null else currentPage - 1,
                nextKey = if (endOfPaginationReached) null else currentPage + 1
            )

        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, CharacterEntity>): Int? {
        return state.anchorPosition
    }

}