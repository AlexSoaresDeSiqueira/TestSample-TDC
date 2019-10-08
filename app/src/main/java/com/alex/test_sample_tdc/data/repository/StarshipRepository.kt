package com.alex.test_sample_tdc.data.repository

import com.alex.test_sample_tdc.data.StarwarsApi
import com.alex.test_sample_tdc.data.model.ResponseStarship
import io.reactivex.Single

class StarshipRepository(
    private val starwarsApi: StarwarsApi
) : StarshipRepositoryContract {

    override fun searchStarshipByName(starshipName: String): Single<ResponseStarship> {
        return starwarsApi.searchStarshipByName(starshipName)
            .doOnError {
                it.stackTrace
            }
    }


}