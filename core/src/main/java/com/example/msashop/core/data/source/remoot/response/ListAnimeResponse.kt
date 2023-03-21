package com.example.msashop.core.data.source.remoot.response

import com.google.gson.annotations.SerializedName

data class ListAnimeResponse(
    @SerializedName("data") val animeList: List<AnimeResponse>
)