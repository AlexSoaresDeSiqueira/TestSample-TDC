package com.alex.test_sample_tdc.data

import com.alex.test_sample_tdc.data.model.ResponseStarship
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface StarwarsApi {

    @GET("starships/?format=json")
    fun searchStarshipByName(@Query("search") starshipName: String) : Single<ResponseStarship>
}