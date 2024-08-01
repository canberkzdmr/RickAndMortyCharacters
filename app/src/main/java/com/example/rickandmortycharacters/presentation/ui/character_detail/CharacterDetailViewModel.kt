package com.example.rickandmortycharacters.presentation.ui.character_detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rickandmortycharacters.domain.CharacterDetailUseCase
import com.example.rickandmortycharacters.domain.EpisodesUseCase
import com.example.rickandmortycharacters.network.service.RickAndMortyService
import com.example.rickandmortycharacters.network.model.character_detail.CharacterDetail
import com.example.rickandmortycharacters.network.model.character_detail.Episode
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class CharacterDetailViewModel @Inject constructor(
    private val characterDetailUseCase: CharacterDetailUseCase,
    private val episodesUseCase: EpisodesUseCase
)  : ViewModel() {

    private val _character = MutableLiveData<CharacterDetail>()
    val character: LiveData<CharacterDetail> = _character

    private val _episodes = MutableLiveData<List<Episode>>()
    val episodes: LiveData<List<Episode>> = _episodes

    fun fetchCharacterDetail(characterId: Int) {
        viewModelScope.launch {
            val character = characterDetailUseCase(characterId)
            _character.value = character
            _episodes.value = episodesUseCase.invoke(character.episode)
        }
    }
}