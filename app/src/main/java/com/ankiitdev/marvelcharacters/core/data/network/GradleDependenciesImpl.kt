package com.ankiitdev.marvelcharacters.core.data.network

import com.ankiitdev.marvelcharacters.BuildConfig
import javax.inject.Inject

class GradleDependenciesImpl @Inject constructor() : GradleDependencies {

    override val apiBaseUrl: String
        get() = BuildConfig.API_BASE_URL

    override val marvelPublicApiKey: String
        get() = BuildConfig.MARVEL_PUBLIC_API_KEY

    override val marvelPrivateApiKey: String
        get() = BuildConfig.MARVEL_PRIVATE_API_KEY

    override val marvelApiKey: String
        get() = BuildConfig.MARVEL_API_KEY
}