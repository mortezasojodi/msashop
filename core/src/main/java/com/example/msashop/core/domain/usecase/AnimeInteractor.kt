package com.example.msashop.core.domain.usecase

import com.example.msashop.core.data.Resource
import com.example.msashop.core.domain.model.Product
import com.example.msashop.core.domain.repository.IAnimeRepository
import kotlinx.coroutines.flow.Flow

class AnimeInteractor(private val animeRepository: IAnimeRepository): ProductUseCase {
    override fun getAllAnime(): Flow<Resource<List<Product>>> {
        return animeRepository.getAllAnime()
    }

    override fun getFavoriteAnime(): Flow<List<Product>> {
        return animeRepository.getFavoriteAnime()
    }

    override fun setFavoriteAnime(anime: Product, state: Boolean) {
        return animeRepository.setFavoriteAnime(anime, state)
    }
}