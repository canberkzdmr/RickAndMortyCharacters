package com.example.rickandmortycharacters.network.model.character_detail

import com.google.gson.annotations.SerializedName

data class CharacterDetail(
    @SerializedName("name") var name: String? = null,
    @SerializedName("status") var status: String? = null,
    @SerializedName("species") var species: String? = null,
    @SerializedName("gender") var gender: String? = null,
    @SerializedName("image") var image: String? = null,
    @SerializedName("episode") var episode: ArrayList<String> = arrayListOf(),
    @SerializedName("created") var created: String? = null,
)
