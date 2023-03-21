package com.example.msashop.core.data.source.remoot

import com.example.msashop.core.data.source.remoot.network.ApiResponse
import com.example.msashop.core.data.source.remoot.network.ApiService
import com.example.msashop.core.data.source.remoot.response.AnimeResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class RemoteDataSource(private val apiService: ApiService) {

    fun getAllAnime(): Flow<ApiResponse<List<AnimeResponse>>> {
        return flow {
            try {
                val response = apiService.getAnimeList()
                val dataArray = response.animeList
                if (dataArray.isNotEmpty()) {
                    emit(ApiResponse.Success(response.animeList))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
            }
        }.flowOn(Dispatchers.IO)
    }
}