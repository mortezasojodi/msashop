package com.example.msashop.di

import com.example.msashop.Home.HomeViewModel
import com.example.msashop.core.domain.usecase.AnimeInteractor
import com.example.msashop.core.domain.usecase.ProductUseCase
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


val useCaseModule = module {
    factory<ProductUseCase> { AnimeInteractor(get()) }
}

val viewModelModule = module {
    viewModel { HomeViewModel(get()) }
//    viewModel { DetailAnimeViewModel(get()) }
}