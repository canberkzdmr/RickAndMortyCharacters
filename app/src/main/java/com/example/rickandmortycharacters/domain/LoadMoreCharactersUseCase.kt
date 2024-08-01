package com.example.rickandmortycharacters.domain

import com.example.rickandmortycharacters.network.service.RickAndMortyService
import com.example.rickandmortycharacters.network.model.characters.CharacterResponse
import javax.inject.Inject

class LoadMoreCharactersUseCase @Inject constructor(private val apiService: RickAndMortyService) {
    private var currentPage = 1

    suspend operator fun invoke(): CharacterResponse {
        val response = apiService.fetchCharacters(currentPage).body() ?: CharacterResponse()
        currentPage++
        return response
    }
}