package com.alex.test_sample_tdc.feature.searchStarship

import com.alex.test_sample_tdc.data.model.Starship
import com.alex.test_sample_tdc.feature.searchStarship.model.StarshipView

class SearchStarshipReducer : SearchStarshipReducerContract {

    override fun reducer(
        currentState: SearchStarshipState?,
        nextState: SearchStarshipStates
    ) =
        currentState?.let {
            when (nextState) {
                is SearchStarshipStates.Loading -> it.copy(
                    isLoading = true
                )

                is SearchStarshipStates.Success -> it.copy(
                    isLoading = false,
                    starship = convertStarshipInStarshipView(nextState.starship)
                )
            }
        } ?: SearchStarshipState()

    private fun convertStarshipInStarshipView(starship: Starship) =
        StarshipView(name = starship.name, model = starship.model, starshipClass = starship.starship_class)
}

interface SearchStarshipReducerContract {
    fun reducer(
        currentState: SearchStarshipState?,
        nextState: SearchStarshipStates
    ): SearchStarshipState
}