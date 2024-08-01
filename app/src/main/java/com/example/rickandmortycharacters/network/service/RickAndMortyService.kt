package com.example.rickandmortycharacters.network.service

import com.example.rickandmortycharacters.network.model.character_detail.CharacterDetail
import com.example.rickandmortycharacters.network.model.character_detail.Episode
import com.example.rickandmortycharacters.network.model.characters.CharacterResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import retrofit2.http.Url

interface RickAndMortyService {

    @GET("character")
    suspend fun fetchCharacters(
        @Query("page") page: Int,
    ): Response<CharacterResponse>

    @GET("character/{id}")
    suspend fun fetchCharacterById(
        @Path("id") id: Int,
    ): Response<CharacterDetail>

    @GET
    suspend fun getEpisode(
        @Url url: String,
    ): Episode
}
