package com.ankiitdev.marvelcharacters.core.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.ankiitdev.marvelcharacters.core.models.remotemediator.CharacterRemoteKeys

@Dao
interface CharactersRemoteKeysDao {

    @Query("SELECT * FROM marvel_characters_remote_keys WHERE id =:id")
    suspend fun getRemoteKeys(id: String): CharacterRemoteKeys

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addAllRemoteKeys(remoteKeys: List<CharacterRemoteKeys>)

    @Query("DELETE FROM marvel_characters_remote_keys")
    suspend fun deleteAllRemoteKeys()

}