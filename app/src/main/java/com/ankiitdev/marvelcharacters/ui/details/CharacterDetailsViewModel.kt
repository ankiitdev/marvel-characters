package com.ankiitdev.marvelcharacters.ui.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ankiitdev.marvelcharacters.core.data.repo.LocalRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharacterDetailsViewModel @Inject constructor(
    private val localRepo: LocalRepo
) : ViewModel() {

    fun incrementCount(id: Int) {
        viewModelScope.launch {
            localRepo.incrementVisitCount(id)
        }
    }

}