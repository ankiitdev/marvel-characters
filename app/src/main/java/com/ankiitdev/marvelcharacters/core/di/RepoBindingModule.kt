package com.ankiitdev.marvelcharacters.core.di

import com.ankiitdev.marvelcharacters.core.data.repo.LocalRepo
import com.ankiitdev.marvelcharacters.core.data.repo.LocalRepoImpl
import com.ankiitdev.marvelcharacters.core.data.repo.RemoteRepo
import com.ankiitdev.marvelcharacters.core.data.repo.RemoteRepoImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface RepoBindingModule {

    @Binds
    @Singleton
    fun bindRepo(repo: RemoteRepoImpl): RemoteRepo

    @Binds
    @Singleton
    fun bindLocalRepo(localRepo: LocalRepoImpl): LocalRepo

}