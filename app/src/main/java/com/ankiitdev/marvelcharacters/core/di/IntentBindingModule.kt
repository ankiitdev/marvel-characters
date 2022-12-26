package com.ankiitdev.marvelcharacters.core.di

import com.ankiitdev.marvelcharacters.core.resolvers.IntentResolver
import com.ankiitdev.marvelcharacters.core.resolvers.IntentResolverImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface IntentBindingModule {

    @Binds
    @Singleton
    fun intentResolver(intentResolver: IntentResolverImpl): IntentResolver

}