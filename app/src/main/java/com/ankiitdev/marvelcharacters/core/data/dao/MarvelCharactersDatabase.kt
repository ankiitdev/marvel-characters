package com.ankiitdev.marvelcharacters.core.data.dao

import androidx.room.Database
import androidx.room.RoomDatabase
import com.ankiitdev.marvelcharacters.core.models.CharacterEntity
import com.ankiitdev.marvelcharacters.core.models.remotemediator.CharacterRemoteKeys

@Database(entities = [CharacterEntity::class, CharacterRemoteKeys::class], version = 1)
abstract class MarvelCharactersDatabase : RoomDatabase() {
    abstract fun charactersDao(): CharactersDao
    abstract fun charactersRemoteKeysDao(): CharactersRemoteKeysDao
}