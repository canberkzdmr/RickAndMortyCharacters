package com.example.rickandmortycharacters.di

import com.example.rickandmortycharacters.domain.CharacterDetailUseCase
import com.example.rickandmortycharacters.domain.EpisodesUseCase
import com.example.rickandmortycharacters.domain.LoadMoreCharactersUseCase
import com.example.rickandmortycharacters.network.service.RickAndMortyService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {

    @Provides
    @Singleton
    fun provideLoadMoreCharactersUseCase(apiService: RickAndMortyService): LoadMoreCharactersUseCase {
        return LoadMoreCharactersUseCase(apiService)
    }

    @Provides
    @Singleton
    fun provideCharacterDetailUseCase(apiService: RickAndMortyService): CharacterDetailUseCase {
        return CharacterDetailUseCase(apiService)
    }

    @Provides
    @Singleton
    fun provideEpisodeUseCase(apiService: RickAndMortyService): EpisodesUseCase {
        return EpisodesUseCase(apiService)
    }
}