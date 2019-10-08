package com.alex.test_sample_tdc.feature

import com.alex.test_sample_tdc.feature.searchStarship.SearchStarshipActivity
import com.alex.test_sample_tdc.rules.InstrumentedTestRule
import org.junit.Rule
import org.junit.Test

class SearchStarshipActivityTest {

    @get:Rule
    var instrumentedTestRule = InstrumentedTestRule(SearchStarshipActivity::class.java)

    @Test
    fun whenUserClickOnSearchButton_shouldShowShipDetailWhoWasSearched() {
        setupStarshipActivity {
            mockDeathStarship(instrumentedTestRule.serverRule)
        } launch {
            searchStarship("death")
        } check {
            starshipDetails(
                name = "Death Star",
                model = "DS-1 Orbital Battle Station",
                starshipClass = "Deep Space Mobile Battlestation"
            )
        }
    }

}