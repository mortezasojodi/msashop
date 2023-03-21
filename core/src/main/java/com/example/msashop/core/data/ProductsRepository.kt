package com.example.msashop.core.data

import com.example.msashop.core.data.source.local.LocalDataSource
import com.example.msashop.core.data.source.remoot.RemoteDataSource
import com.example.msashop.core.data.source.remoot.network.ApiResponse
import com.example.msashop.core.data.source.remoot.response.AnimeResponse
import com.example.msashop.core.domain.model.Product
import com.example.msashop.core.domain.repository.IAnimeRepository
import com.example.msashop.core.utils.AppExecutors
import com.example.msashop.core.utils.DataMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class ProductsRepository(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
) : IAnimeRepository {

    override fun getAllAnime(): Flow<Resource<List<Product>>> =
        object : NetworkBoundResource<List<Product>, List<AnimeResponse>>() {
            override fun loadFromDB(): Flow<List<Product>> {
                return localDataSource.getAllAnime().map { DataMapper.mapEntitiesToDomain(it) }
            }

            override fun shouldFetch(data: List<Product>?): Boolean {
                return true// data == null || data.isEmpty()
            }

            override suspend fun createCall(): Flow<ApiResponse<List<AnimeResponse>>> {
                return remoteDataSource.getAllAnime()
            }

            override suspend fun saveCallResult(data: List<AnimeResponse>) {
                val animeList = DataMapper.mapResponsesToEntities(data)
                localDataSource.insertAnime(animeList)
            }
        }.asFlow()

    override fun getFavoriteAnime(): Flow<List<Product>> {
        return localDataSource.getFavoriteAnime().map { DataMapper.mapEntitiesToDomain(it) }
    }

    override fun setFavoriteAnime(anime: Product, state: Boolean) {
        val animeEntity = DataMapper.mapDomainToEntity(anime)
        appExecutors.diskIO().execute {
            localDataSource.setFavoriteAnime(animeEntity, state)
        }
    }
}