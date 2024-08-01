package com.example.rickandmortycharacters.domain

import com.example.rickandmortycharacters.network.model.character_detail.Episode
import com.example.rickandmortycharacters.network.service.RickAndMortyService
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope
import javax.inject.Inject

class EpisodesUseCase @Inject constructor(private val apiService: RickAndMortyService) {

    suspend operator fun invoke(episodeUrls: List<String>): List<Episode> = coroutineScope{
        episodeUrls.map { url ->
            async {
                apiService.getEpisode(url)
            }
        }.awaitAll()
    }
}