package com.alex.test_sample_tdc.di

import com.alex.test_sample_tdc.data.repository.StarshipRepository
import com.alex.test_sample_tdc.data.repository.StarshipRepositoryContract
import org.koin.dsl.module

val repositoryModule = module {
    single<StarshipRepositoryContract> { StarshipRepository(get()) }
}