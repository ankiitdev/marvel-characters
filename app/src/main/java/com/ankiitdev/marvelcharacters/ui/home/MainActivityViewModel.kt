package com.ankiitdev.marvelcharacters.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.*
import com.ankiitdev.marvelcharacters.core.constants.Constants
import com.ankiitdev.marvelcharacters.core.data.dao.MarvelCharactersDatabase
import com.ankiitdev.marvelcharacters.core.data.network.service.ApiService
import com.ankiitdev.marvelcharacters.core.data.network.utils.Resource
import com.ankiitdev.marvelcharacters.core.data.paging.CharactersPagingSource
import com.ankiitdev.marvelcharacters.core.data.paging.CharactersRemoteMediator
import com.ankiitdev.marvelcharacters.core.data.repo.LocalRepo
import com.ankiitdev.marvelcharacters.core.models.CharacterEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor(
    private val localRepo: LocalRepo,
    private val apiService: ApiService,
    private val database: MarvelCharactersDatabase
) : ViewModel() {

    private val _characters = MutableSharedFlow<Resource<List<CharacterEntity>>>()

    val characters: SharedFlow<Resource<List<CharacterEntity>>>
        get() = _characters

    // GET LIST WITHOUT PAGING3
    fun getCharacters() {
        viewModelScope.launch {
            localRepo.getAllCharacters().collect {
                _characters.emit(it)
            }
        }
    }

//    fun getAllCharacters(): Flow<PagingData<CharacterEntity>> {
//        return Pager(
//            config = PagingConfig(pageSize = 20),
//            pagingSourceFactory = {
//                CharactersPagingSource(api = apiService)
//            }
//        ).flow
//    }

    @OptIn(ExperimentalPagingApi::class)
    val charactersPager by lazy {
        Pager(
            PagingConfig(pageSize = 20, prefetchDistance = 15, initialLoadSize = 60),
            remoteMediator = CharactersRemoteMediator(apiService, database)
        ) {
            database.charactersDao().getAllCharacters()
        }.flow.distinctUntilChanged().cachedIn(viewModelScope)
    }

}