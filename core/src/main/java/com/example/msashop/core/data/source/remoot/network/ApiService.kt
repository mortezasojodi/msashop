package com.example.msashop.core.data.source.remoot.network

import com.example.msashop.core.data.source.remoot.response.ListAnimeResponse
import retrofit2.http.GET

interface ApiService {
    @GET("trending/anime")
    suspend fun getAnimeList(): ListAnimeResponse
}