package com.tomczyn.linkding.data

@kotlinx.serialization.Serializable
data class BookmarksResponse(
    val count: Int,
    val next: String?,
    val previous: String?,
    val results: List<Bookmark>
)
