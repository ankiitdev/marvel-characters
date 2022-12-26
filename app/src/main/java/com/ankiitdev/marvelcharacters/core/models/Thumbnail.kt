package com.ankiitdev.marvelcharacters.core.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable

@Parcelize
@Serializable
data class Thumbnail constructor(
    val path: String,
    val extension: String
) : Parcelable