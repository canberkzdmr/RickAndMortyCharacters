package com.example.rickandmortycharacters.domain

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.rickandmortycharacters.network.model.characters.Character
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PaginatedDataSource
    @Inject
    constructor(
        private val loadMoreCharactersUseCase: LoadMoreCharactersUseCase,
    ) {
        private var isLoading = false
        private var isLastPage = false

        private val _characters = MutableLiveData<List<Character>>()
        val characters: LiveData<List<Character>> = _characters

        suspend fun loadMoreCharacters() {
            if (isLoading || isLastPage) return

            isLoading = true
            val response = loadMoreCharactersUseCase()
            val newCharacters = response.results
            _characters.value = _characters.value.orEmpty() + newCharacters
            isLastPage = response.info?.next == null
            isLoading = false
        }

        fun isLoading() = isLoading

        fun isLastPage() = isLastPage
    }
