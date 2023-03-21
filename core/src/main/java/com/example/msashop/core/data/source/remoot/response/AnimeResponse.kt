package com.example.msashop.core.data.source.remoot.response

import com.google.gson.annotations.SerializedName

data class AnimeResponse(
    @SerializedName("id")
    val id : Int,
    @SerializedName("attributes")
    val attributes : Attributes
)

