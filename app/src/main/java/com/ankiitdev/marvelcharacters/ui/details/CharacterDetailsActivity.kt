package com.ankiitdev.marvelcharacters.ui.details

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import androidx.activity.viewModels
import com.ankiitdev.marvelcharacters.R
import com.ankiitdev.marvelcharacters.core.models.CharacterEntity
import com.ankiitdev.marvelcharacters.core.utils.BaseActivity
import com.ankiitdev.marvelcharacters.databinding.ActivityCharacterDetailsBinding
import com.bumptech.glide.Glide
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CharacterDetailsActivity : BaseActivity<ActivityCharacterDetailsBinding>() {

    private val viewModel: CharacterDetailsViewModel by viewModels()

    private lateinit var args: CharacterEntity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setSupportActionBar(binding.toolbar)
        supportActionBar?.title = getString(R.string.str_marvel_character_details)
        args = (savedInstanceState ?: intent.extras)?.getParcelable(ARG_MARVEL_CHARACTER)!!
        viewModel.incrementCount(args.id)
        setData()
    }

    private fun setData() {
        args.apply {
            val url = "${this.thumbnail.path}.${this.thumbnail.extension}"
            Glide.with(binding.root.context)
                .load(url)
                .into(binding.imageCharacter)
            binding.characterName.text = this.name
            if (this.description.isEmpty()) {
                binding.characterDesc.text = getString(R.string.str_desc_error)
            } else {
                binding.characterDesc.text = this.description
            }
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putParcelable(ARG_MARVEL_CHARACTER, args)
    }

    override fun inflateLayout(inflater: LayoutInflater): ActivityCharacterDetailsBinding {
        return ActivityCharacterDetailsBinding.inflate(layoutInflater)
    }

    companion object {
        private const val ARG_MARVEL_CHARACTER = "marvel_character"
        fun getIntent(
            context: Context?,
            character: CharacterEntity
        ) = Intent(context, CharacterDetailsActivity::class.java)
            .apply {
                putExtra(ARG_MARVEL_CHARACTER, character)
            }
    }

}