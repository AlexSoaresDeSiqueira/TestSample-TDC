package com.alex.test_sample_tdc.data.repository

import com.alex.test_sample_tdc.data.model.ResponseStarship
import io.reactivex.Single

interface StarshipRepositoryContract {
    fun searchStarshipByName(starshipName: String) : Single<ResponseStarship>
}