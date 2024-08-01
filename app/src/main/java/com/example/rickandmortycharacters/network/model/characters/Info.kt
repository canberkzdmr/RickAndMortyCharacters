package com.example.rickandmortycharacters.network.model.characters

import com.google.gson.annotations.SerializedName

data class Info(
    @SerializedName("next") var next: String? = null,
)
