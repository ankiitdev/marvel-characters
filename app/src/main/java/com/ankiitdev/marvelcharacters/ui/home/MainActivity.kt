package com.ankiitdev.marvelcharacters.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.paging.map
import androidx.recyclerview.widget.LinearLayoutManager
import com.ankiitdev.marvelcharacters.R
import com.ankiitdev.marvelcharacters.adapter.MarvelCharactersAdapter
import com.ankiitdev.marvelcharacters.adapter.MarvelCharactersPagingAdapter
import com.ankiitdev.marvelcharacters.core.data.network.utils.Resource
import com.ankiitdev.marvelcharacters.core.utils.BaseActivity
import com.ankiitdev.marvelcharacters.core.utils.hide
import com.ankiitdev.marvelcharacters.core.utils.show
import com.ankiitdev.marvelcharacters.core.utils.showSnackbar
import com.ankiitdev.marvelcharacters.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>() {

    private val viewModel: MainActivityViewModel by viewModels()

    private val charactersAdapter: MarvelCharactersPagingAdapter by lazy {
        MarvelCharactersPagingAdapter(onClick = { character ->
            startActivity(intentResolver.getCharacterDetailsActivity(this, character = character))
        })
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setSupportActionBar(binding.toolbar)
        supportActionBar?.title = getString(R.string.str_marvel_app)
        setupAdapter()
        observe()
//        viewModel.getCharacters()
    }

    override fun inflateLayout(inflater: LayoutInflater): ActivityMainBinding {
        return ActivityMainBinding.inflate(layoutInflater)
    }

    private fun observe() {
        lifecycleScope.launch {
            // GET LIST WITHOUT PAGING3
//            viewModel.characters.collect { response ->
//                when (response) {
//                    is Resource.Loading -> {
//                        binding.mainProgress.root.show()
//                    }
//                    is Resource.Failure -> {
//                        binding.mainProgress.root.hide()
//                        binding.root.showSnackbar(getString(R.string.str_error_message))
//                    }
//                    is Resource.Success -> {
//                        binding.mainProgress.root.hide()
//                        response.data.let {
//                            charactersAdapter.submitList(it)
//                        }
//                    }
//                }
//            }
            viewModel.charactersPager.collectLatest { pagingData ->
                charactersAdapter.submitData(pagingData)
            }
        }
    }

    private fun setupAdapter() {
        binding.listCharacters.apply {
            adapter = charactersAdapter
            layoutManager = LinearLayoutManager(this.context)
        }
    }

    companion object {
        private const val TAG = "MainActivity"
    }
}