package com.alex.test_sample_tdc.feature

import com.alex.test_sample_tdc.di.networkModule
import com.alex.test_sample_tdc.di.repositoryModule
import com.alex.test_sample_tdc.feature.searchStarship.SearchStarshipState
import com.alex.test_sample_tdc.feature.searchStarship.SearchStarshipViewModel
import com.alex.test_sample_tdc.feature.searchStarship.di.searchStarshipModule
import com.alex.test_sample_tdc.feature.searchStarship.model.StarshipView
import com.alex.test_sample_tdc.rules.LocalTestRule
import com.alex.test_sample_tdc.rules.RxLocalRule
import okhttp3.mockwebserver.MockResponse
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.koin.test.KoinTest
import org.koin.test.inject

@RunWith(JUnit4::class)
class SearchStarshipViewModelTest : KoinTest {

    private val starshipViewModel: SearchStarshipViewModel by inject()

    @get:Rule
    val rxRule = RxLocalRule()

    @get:Rule
    val localTestRule = LocalTestRule(
        mutableListOf(
            networkModule,
            repositoryModule,
            searchStarshipModule
        )
    )

    @Test
    fun `searchStarship should return success when pass a valid starship name`() {
        // given
        mockStarshipSuccessRequest()
        val starshipExpected = mockDeathStarship()
        var starshipActual = SearchStarshipState()

        starshipViewModel.states.observeForever { state ->
            state.let {
                starshipActual = it
            }
        }

        // when
        starshipViewModel.searchStarship("death")

        // then
        assertEquals(starshipExpected, starshipActual)
    }

    private fun mockStarshipSuccessRequest() {
        localTestRule.serverRule.enqueue(MockResponse().setBody("{\n" +
                "    \"count\": 1,\n" +
                "    \"next\": null,\n" +
                "    \"previous\": null,\n" +
                "    \"results\": [\n" +
                "        {\n" +
                "            \"name\": \"Death Star\",\n" +
                "            \"model\": \"DS-1 Orbital Battle Station\",\n" +
                "            \"manufacturer\": \"Imperial Department of Military Research, Sienar Fleet Systems\",\n" +
                "            \"cost_in_credits\": \"1000000000000\",\n" +
                "            \"length\": \"120000\",\n" +
                "            \"max_atmosphering_speed\": \"n/a\",\n" +
                "            \"crew\": \"342953\",\n" +
                "            \"passengers\": \"843342\",\n" +
                "            \"cargo_capacity\": \"1000000000000\",\n" +
                "            \"consumables\": \"3 years\",\n" +
                "            \"hyperdrive_rating\": \"4.0\",\n" +
                "            \"MGLT\": \"10\",\n" +
                "            \"starship_class\": \"Deep Space Mobile Battlestation\",\n" +
                "            \"pilots\": [],\n" +
                "            \"films\": [\n" +
                "                \"https://swapi.co/api/films/1/\"\n" +
                "            ],\n" +
                "            \"created\": \"2014-12-10T16:36:50.509000Z\",\n" +
                "            \"edited\": \"2014-12-22T17:35:44.452589Z\",\n" +
                "            \"url\": \"https://swapi.co/api/starships/9/\"\n" +
                "        }\n" +
                "    ]\n" +
                "}"))
    }

    private fun mockDeathStarship() =
        SearchStarshipState(
            isLoading = false,
            starship = StarshipView(
                "Death Star",
                "DS-1 Orbital Battle Station",
                "Deep Space Mobile Battlestation")
        )
}