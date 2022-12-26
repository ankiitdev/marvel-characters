package com.ankiitdev.marvelcharacters.core.models

import android.os.Parcelable
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.ankiitdev.marvelcharacters.core.constants.Constants.CHARACTERS_TABLE
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable

@Serializable
@Parcelize
@Entity(tableName = CHARACTERS_TABLE)
data class CharacterEntity constructor(
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val name: String,
    val description: String,
    @Embedded
    val thumbnail: Thumbnail,
    val visitCount: Int = 0
) : Parcelable