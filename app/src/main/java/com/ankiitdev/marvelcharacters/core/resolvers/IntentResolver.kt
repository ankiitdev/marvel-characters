package com.ankiitdev.marvelcharacters.core.resolvers

import android.content.Context
import android.content.Intent
import com.ankiitdev.marvelcharacters.core.models.CharacterEntity

interface IntentResolver {

    fun getCharacterDetailsActivity(context: Context?, character: CharacterEntity): Intent

}