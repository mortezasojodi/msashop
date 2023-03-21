package com.example.msashop.core.utils

import com.example.msashop.core.data.source.local.entity.AnimeEntity
import com.example.msashop.core.data.source.remoot.response.AnimeResponse
import com.example.msashop.core.domain.model.Product

object DataMapper {

    fun mapResponsesToEntities(input: List<AnimeResponse>): List<AnimeEntity> {
        val animeList = ArrayList<AnimeEntity>()
        input.map {
            val anime = AnimeEntity(

                id = it.id,
                title = it.attributes.title,
                price = it.attributes.price,
                category = it.attributes.category,
                description = it.attributes.description,
                image = it.attributes.image,
            )
            animeList.add(anime)
        }
        return animeList
    }

    fun mapEntitiesToDomain(input: List<AnimeEntity>): List<Product> =
        input.map {
            Product(
                id = it.id,
                title = it.title,
                price = it.price,
                category = it.category,
                description = it.description,
                image = it.image,

            )
        }

    fun mapDomainToEntity(input: Product) = AnimeEntity(
        id = input.id,
        title = input.title,
        price = input.price,
        category = input.category,
        description = input.description,
        image = input.image,
    )
}