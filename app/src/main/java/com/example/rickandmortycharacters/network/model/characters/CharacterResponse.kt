package com.example.rickandmortycharacters.network.model.characters

import com.google.gson.annotations.SerializedName

data class CharacterResponse(
    @SerializedName("info") var info: Info? = Info(),
    @SerializedName("results") var results: ArrayList<Character> = arrayListOf(),
)
