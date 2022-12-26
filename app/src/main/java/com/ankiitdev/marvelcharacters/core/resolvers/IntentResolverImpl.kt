package com.ankiitdev.marvelcharacters.core.resolvers

import android.content.Context
import android.content.Intent
import com.ankiitdev.marvelcharacters.core.models.CharacterEntity
import com.ankiitdev.marvelcharacters.ui.details.CharacterDetailsActivity
import javax.inject.Inject

class IntentResolverImpl @Inject constructor() : IntentResolver {

    override fun getCharacterDetailsActivity(
        context: Context?,
        character: CharacterEntity
    ): Intent {
        return CharacterDetailsActivity.getIntent(context, character)
    }
}