package com.example.msashop.core.domain.usecase

import com.example.msashop.core.data.Resource
import com.example.msashop.core.domain.model.Product
import kotlinx.coroutines.flow.Flow

interface ProductUseCase {
    fun getAllAnime(): Flow<Resource<List<Product>>>
    fun getFavoriteAnime(): Flow<List<Product>>
    fun setFavoriteAnime(anime: Product, state: Boolean)
}