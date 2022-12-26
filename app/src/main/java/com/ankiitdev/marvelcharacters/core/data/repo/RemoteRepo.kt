package com.ankiitdev.marvelcharacters.core.data.repo

import com.ankiitdev.marvelcharacters.core.models.CharactersResponse

interface RemoteRepo {
    suspend fun getCharacters(): CharactersResponse
}