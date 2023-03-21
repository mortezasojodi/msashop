package com.example.msashop.Home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.msashop.core.domain.model.Product
import com.example.msashop.core.domain.usecase.ProductUseCase


class HomeViewModel(animeUseCase: ProductUseCase) : ViewModel() {

    val anime = animeUseCase.getAllAnime().asLiveData()

}