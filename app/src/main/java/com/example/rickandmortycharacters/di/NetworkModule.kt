package com.example.rickandmortycharacters.di

import com.example.rickandmortycharacters.domain.CharacterDetailUseCase
import com.example.rickandmortycharacters.domain.EpisodesUseCase
import com.example.rickandmortycharacters.domain.LoadMoreCharactersUseCase
import com.example.rickandmortycharacters.network.service.RickAndMortyService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://rickandmortyapi.com/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): RickAndMortyService {
        return retrofit.create(RickAndMortyService::class.java)
    }
}