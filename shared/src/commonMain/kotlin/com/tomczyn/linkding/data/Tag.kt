package com.tomczyn.linkding.data

import kotlinx.serialization.Serializable

@Serializable
data class TagsResponse(
    val count: Int,
    val next: String?,
    val previous: String?,
    val results: List<Tag>
)

@Serializable
data class Tag(
    val name: String
)
