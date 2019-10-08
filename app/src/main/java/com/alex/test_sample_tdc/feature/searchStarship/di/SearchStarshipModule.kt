package com.alex.test_sample_tdc.feature.searchStarship.di

import com.alex.test_sample_tdc.feature.searchStarship.SearchStarshipReducer
import com.alex.test_sample_tdc.feature.searchStarship.SearchStarshipReducerContract
import com.alex.test_sample_tdc.feature.searchStarship.SearchStarshipViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val searchStarshipModule = module {

    factory<SearchStarshipReducerContract> { SearchStarshipReducer() }

    viewModel { SearchStarshipViewModel(get(), get()) }
}