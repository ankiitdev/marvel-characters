package com.ankiitdev.marvelcharacters.core.data.network.service

import com.ankiitdev.marvelcharacters.core.constants.Constants.ITEMS_PER_PAGE
import com.ankiitdev.marvelcharacters.core.models.CharactersResponse
import retrofit2.http.*
import java.util.*

interface ApiService {

    @GET("v1/public/characters")
    suspend fun getCharacters(
        @Query("limit") limit: String = ITEMS_PER_PAGE,
    ): CharactersResponse

}