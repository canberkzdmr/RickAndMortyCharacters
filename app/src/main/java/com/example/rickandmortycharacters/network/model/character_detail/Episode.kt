package com.example.rickandmortycharacters.network.model.character_detail

import com.google.gson.annotations.SerializedName

data class Episode(
    @SerializedName("name") var name: String? = null,
)
