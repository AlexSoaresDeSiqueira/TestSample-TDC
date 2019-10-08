package com.alex.test_sample_tdc.data.model

data class Starship(
    val url: String,
    val name: String,
    val model: String,
    val starship_class: String
)

data class ResponseStarship(
    val results: List<Starship>
)