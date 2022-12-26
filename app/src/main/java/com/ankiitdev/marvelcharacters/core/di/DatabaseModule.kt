package com.ankiitdev.marvelcharacters.core.di

import android.content.Context
import androidx.room.Room
import com.ankiitdev.marvelcharacters.core.constants.Constants.MARVEL_DB
import com.ankiitdev.marvelcharacters.core.data.dao.MarvelCharactersDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(
        @ApplicationContext context: Context
    ): MarvelCharactersDatabase {
        return Room.databaseBuilder(
            context,
            MarvelCharactersDatabase::class.java,
            MARVEL_DB
        ).build()
    }

}