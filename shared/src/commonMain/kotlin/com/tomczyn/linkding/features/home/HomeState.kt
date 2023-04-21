package com.tomczyn.linkding.features.home

data class HomeState(
    val search: String = "",
    val tags: List<HomeScreenTag> = emptyList(),
    val tagsSearch: List<HomeScreenTag> = emptyList(),
    val error: Boolean = false,
)
