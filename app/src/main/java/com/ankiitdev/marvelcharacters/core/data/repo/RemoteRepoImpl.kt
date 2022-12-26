package com.ankiitdev.marvelcharacters.core.data.repo

import com.ankiitdev.marvelcharacters.core.data.network.service.ApiService
import com.ankiitdev.marvelcharacters.core.models.CharactersResponse
import javax.inject.Inject

class RemoteRepoImpl @Inject constructor(
    private val service: ApiService
) : RemoteRepo {

    override suspend fun getCharacters(): CharactersResponse {
        return service.getCharacters()
    }
}