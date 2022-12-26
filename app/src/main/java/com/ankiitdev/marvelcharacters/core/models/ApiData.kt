package com.ankiitdev.marvelcharacters.core.models

import kotlinx.serialization.Serializable

@Serializable
data class ApiData constructor(
    val results: List<CharacterEntity>
)