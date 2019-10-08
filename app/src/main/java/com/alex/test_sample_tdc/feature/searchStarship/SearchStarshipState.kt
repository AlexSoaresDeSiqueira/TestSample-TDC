package com.alex.test_sample_tdc.feature.searchStarship

import com.alex.test_sample_tdc.data.model.Starship
import com.alex.test_sample_tdc.feature.searchStarship.model.StarshipView

data class SearchStarshipState(
    val isLoading: Boolean = false,
    val starship: StarshipView = StarshipView()
)

sealed class SearchStarshipStates {
    object Loading : SearchStarshipStates()
    data class Success(val starship: Starship) : SearchStarshipStates()
}