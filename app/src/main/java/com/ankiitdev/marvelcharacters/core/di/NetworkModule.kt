package com.ankiitdev.marvelcharacters.core.di

import android.content.Context
import com.ankiitdev.marvelcharacters.core.data.network.GradleDependencies
import com.ankiitdev.marvelcharacters.core.data.network.NetworkInterceptor
import com.ankiitdev.marvelcharacters.core.data.network.service.ApiService
import com.chuckerteam.chucker.api.ChuckerInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.ExperimentalSerializationApi
import okhttp3.Cache
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@ExperimentalSerializationApi
@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideHttpClient(
        @ApplicationContext context: Context,
        networkInterceptor: NetworkInterceptor
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .readTimeout(15, TimeUnit.SECONDS)
            .connectTimeout(15, TimeUnit.SECONDS)
            .addInterceptor(ChuckerInterceptor(context))
            .addInterceptor(networkInterceptor)
            .cache(Cache(context.cacheDir, (10 * 10 * 1024).toLong()))
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(
        okHttpClient: OkHttpClient,
        apiBaseUrl: String,
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(apiBaseUrl)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideApi(retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideBaseUrl(
        gradleDependencies: GradleDependencies
    ): String {
        return gradleDependencies.apiBaseUrl
    }

}