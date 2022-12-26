package com.ankiitdev.marvelcharacters.core.models.remotemediator

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.ankiitdev.marvelcharacters.core.constants.Constants.CHARACTERS_REMOTE_KEYS_TABLE

@Entity(tableName = CHARACTERS_REMOTE_KEYS_TABLE)
data class CharacterRemoteKeys(
    @PrimaryKey(autoGenerate = false)
    val id: String,
    val prevPage: Int?,
    val nextPage: Int?
)