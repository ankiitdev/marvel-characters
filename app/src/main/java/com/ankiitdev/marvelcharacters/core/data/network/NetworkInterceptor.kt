package com.ankiitdev.marvelcharacters.core.data.network

import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class NetworkInterceptor @Inject constructor(
    private val gradleDependencies: GradleDependencies
) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request()
        val url = request.url.newBuilder()
            .addQueryParameter("apikey", gradleDependencies.marvelPublicApiKey)
            .addQueryParameter("hash", gradleDependencies.marvelApiKey)
            .addQueryParameter("ts", "1")
            .build()
        request = request.newBuilder().url(url).build()
        return chain.proceed(request)
    }
}