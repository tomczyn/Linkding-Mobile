package com.tomczyn.linkding.features.home

import com.tomczyn.linkding.data.Tag

data class HomeState(
    val search: String = "",
    val tags: List<Tag> = emptyList(),
    val error: Boolean = false,
)
