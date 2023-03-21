package com.example.msashop.core.domain.repository

import com.example.msashop.core.data.Resource
import com.example.msashop.core.domain.model.Product
import kotlinx.coroutines.flow.Flow

interface IAnimeRepository {

    fun getAllAnime(): Flow<Resource<List<Product>>>

    fun getFavoriteAnime(): Flow<List<Product>>

    fun setFavoriteAnime(anime: Product, state: Boolean)

}