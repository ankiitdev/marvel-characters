package com.ankiitdev.marvelcharacters.core.data.repo

import com.ankiitdev.marvelcharacters.core.data.network.utils.Resource
import com.ankiitdev.marvelcharacters.core.models.CharacterEntity
import kotlinx.coroutines.flow.Flow

interface LocalRepo {

    fun getAllCharacters(): Flow<Resource<List<CharacterEntity>>>

    suspend fun incrementVisitCount(id: Int)
}