package com.ankiitdev.marvelcharacters.core.data.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.ankiitdev.marvelcharacters.core.models.CharacterEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface CharactersDao {

    @Query("SELECT * FROM marvel_characters ORDER BY visitCount DESC")
    fun getCharacters(): Flow<List<CharacterEntity>>

    @Query("SELECT * FROM marvel_characters ORDER BY visitCount DESC")
    fun getAllCharacters(): PagingSource<Int, CharacterEntity>

    @Query("UPDATE marvel_characters SET visitCount = visitCount + 1 WHERE id=:id")
    suspend fun incrementVisitCount(id: Int)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addCharacters(images: List<CharacterEntity>)

    @Query("DELETE FROM marvel_characters")
    suspend fun deleteAllCharacters()

}