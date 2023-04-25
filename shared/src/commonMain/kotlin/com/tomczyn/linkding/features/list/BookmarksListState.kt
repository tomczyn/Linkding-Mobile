package com.tomczyn.linkding.features.list

import com.tomczyn.linkding.data.Bookmark

data class BookmarksListState(
    val bookmarks: List<Bookmark> = emptyList(),
)
