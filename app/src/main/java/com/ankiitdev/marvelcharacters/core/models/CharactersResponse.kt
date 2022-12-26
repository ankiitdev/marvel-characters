package com.ankiitdev.marvelcharacters.core.models

import kotlinx.serialization.Serializable

@Serializable
data class CharactersResponse constructor(
    val data: ApiData,
)