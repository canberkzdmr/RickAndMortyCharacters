package com.example.rickandmortycharacters.presentation.ui.character_list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rickandmortycharacters.network.model.characters.Character
import com.example.rickandmortycharacters.domain.PaginatedDataSource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharacterListViewModel
    @Inject
    constructor(private val paginatedDataSource: PaginatedDataSource) : ViewModel() {
        val characters: LiveData<List<Character>> = paginatedDataSource.characters

        private val _isLoading = MutableLiveData(false)
        val isLoading: LiveData<Boolean> = _isLoading

        private val _isLastPage = MutableLiveData(false)
        val isLastPage: LiveData<Boolean> = _isLastPage

        fun loadMoreCharacters() {
            viewModelScope.launch {
                _isLoading.value = true
                paginatedDataSource.loadMoreCharacters()
                _isLoading.value = paginatedDataSource.isLoading()
                _isLastPage.value = paginatedDataSource.isLastPage()
            }
        }
    }
