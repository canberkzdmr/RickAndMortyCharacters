package com.example.rickandmortycharacters.domain

import com.example.rickandmortycharacters.network.model.character_detail.CharacterDetail
import com.example.rickandmortycharacters.network.service.RickAndMortyService
import javax.inject.Inject

class CharacterDetailUseCase @Inject constructor(private val apiService: RickAndMortyService) {

    suspend operator fun invoke(characterId: Int): CharacterDetail {
        return apiService.fetchCharacterById(characterId).body() ?: CharacterDetail()
    }
}