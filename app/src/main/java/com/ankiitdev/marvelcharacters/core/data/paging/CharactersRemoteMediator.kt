package com.ankiitdev.marvelcharacters.core.data.paging

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.ankiitdev.marvelcharacters.core.data.dao.MarvelCharactersDatabase
import com.ankiitdev.marvelcharacters.core.data.network.service.ApiService
import com.ankiitdev.marvelcharacters.core.models.CharacterEntity
import com.ankiitdev.marvelcharacters.core.models.remotemediator.CharacterRemoteKeys
import kotlinx.coroutines.flow.first

// FETCHES LIST WITH CACHING
@ExperimentalPagingApi
class CharactersRemoteMediator(
    private val api: ApiService,
    private val database: MarvelCharactersDatabase
) : RemoteMediator<Int, CharacterEntity>() {

    private val charactersDao = database.charactersDao()
    private val charactersRemoteKeysDao = database.charactersRemoteKeysDao()

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, CharacterEntity>
    ): MediatorResult {
        return try {
            val currentPage = when (loadType) {
                LoadType.REFRESH -> {
                    val remoteKeys = getRemoteKeyClosestToCurrentPosition(state)
                    remoteKeys?.nextPage?.minus(1) ?: 1
                }
                LoadType.PREPEND -> {
                    val remoteKeys = getRemoteKeyForFirstItem(state)
                    val prevPage = remoteKeys?.prevPage
                        ?: return MediatorResult.Success(
                            endOfPaginationReached = remoteKeys != null
                        )
                    prevPage
                }
                LoadType.APPEND -> {
                    val remoteKeys = getRemoteKeyForLastItem(state)
                    val nextPage = remoteKeys?.nextPage
                        ?: return MediatorResult.Success(
                            endOfPaginationReached = remoteKeys != null
                        )
                    nextPage
                }
            }
            val endOfPaginationReached = true
            if (charactersDao.getCharacters().first().isEmpty()) {
                val response = api.getCharacters().data.results
                val prevPage = if (currentPage == 1) null else currentPage - 1
                val nextPage = if (endOfPaginationReached) null else currentPage + 1
                database.withTransaction {
                    if (loadType == LoadType.REFRESH) {
                        charactersDao.deleteAllCharacters()
                        charactersRemoteKeysDao.deleteAllRemoteKeys()
                    }
                    val keys = response.map { character ->
                        CharacterRemoteKeys(
                            id = character.id.toString(),
                            prevPage = prevPage,
                            nextPage = nextPage
                        )
                    }
                    charactersRemoteKeysDao.addAllRemoteKeys(remoteKeys = keys)
                    charactersDao.addCharacters(images = response)
                }
            }
            MediatorResult.Success(endOfPaginationReached = endOfPaginationReached)
        } catch (e: Exception) {
            return MediatorResult.Error(e)
        }
    }

    override suspend fun initialize(): InitializeAction {
        return super.initialize()
    }

    private suspend fun getRemoteKeyClosestToCurrentPosition(
        state: PagingState<Int, CharacterEntity>
    ): CharacterRemoteKeys? {
        return state.anchorPosition?.let { position ->
            state.closestItemToPosition(position)?.id?.let { id ->
                charactersRemoteKeysDao.getRemoteKeys(id = id.toString())
            }
        }
    }

    private suspend fun getRemoteKeyForFirstItem(
        state: PagingState<Int, CharacterEntity>
    ): CharacterRemoteKeys? {
        return state.pages.firstOrNull { it.data.isNotEmpty() }?.data?.firstOrNull()
            ?.let { character ->
                charactersRemoteKeysDao.getRemoteKeys(id = character.id.toString())
            }
    }

    private suspend fun getRemoteKeyForLastItem(
        state: PagingState<Int, CharacterEntity>
    ): CharacterRemoteKeys? {
        return state.pages.lastOrNull { it.data.isNotEmpty() }?.data?.lastOrNull()
            ?.let { character ->
                charactersRemoteKeysDao.getRemoteKeys(id = character.id.toString())
            }
    }

}