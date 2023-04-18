package com.tomczyn.linkding.data.remote

import kotlinx.serialization.Serializable

@Serializable
data class BookmarksResponse(
    val count: Int,
    val next: String?,
    val previous: String?,
    val results: List<BookmarkRemote>
)
