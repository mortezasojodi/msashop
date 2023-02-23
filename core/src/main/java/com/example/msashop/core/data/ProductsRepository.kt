package com.example.msashop.core.data

import com.bumptech.glide.load.engine.Resource
import kotlinx.coroutines.flow.Flow

interface ProductsRepository {
    fun getAllAnime(): Flow<Resource<List<Product>>>
}