package com.ankiitdev.marvelcharacters.core.data.repo

import androidx.room.withTransaction
import com.ankiitdev.marvelcharacters.core.data.dao.MarvelCharactersDatabase
import com.ankiitdev.marvelcharacters.core.data.network.utils.Resource
import com.ankiitdev.marvelcharacters.core.data.network.utils.networkBoundResource
import com.ankiitdev.marvelcharacters.core.models.CharacterEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LocalRepoImpl @Inject constructor(
    private val database: MarvelCharactersDatabase,
    private val remoteRepo: RemoteRepo
) : LocalRepo {

    private val charactersDatabase = database.charactersDao()
    override fun getAllCharacters(): Flow<Resource<List<CharacterEntity>>> = networkBoundResource(
        query = {
            charactersDatabase.getCharacters()
        },
        fetch = {
            remoteRepo.getCharacters()
        },
        saveFetchResult = { charactersResponse ->
            database.withTransaction {
                charactersDatabase.deleteAllCharacters()
                charactersDatabase.addCharacters(charactersResponse.data.results)
            }
        }, shouldFetch = {
            it.isEmpty()
        }
    )

    override suspend fun incrementVisitCount(id: Int) {
        database.charactersDao().incrementVisitCount(id)
    }

}