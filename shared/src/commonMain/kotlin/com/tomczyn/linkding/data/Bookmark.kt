package com.tomczyn.linkding.data

import com.tomczyn.linkding.data.remote.BookmarkRemote
import com.tomczyn.linkding.database.BookmarkEntity
import com.tomczyn.linkding.database.BookmarkWithTags

data class Bookmark(
    val id: Long,
    val url: String,
    val title: String?,
    val description: String?,
    val websiteTitle: String?,
    val websiteDescription: String?,
    val isArchived: Boolean,
    val unread: Boolean,
    val shared: Boolean,
    val tagNames: List<String>,
    val dateAdded: String,
    val dateModified: String,

    )

fun BookmarkRemote.toBookmark(): Bookmark {
    return Bookmark(
        id = id,
        url = url,
        title = title,
        description = description,
        websiteTitle = websiteTitle,
        websiteDescription = websiteDescription,
        isArchived = isArchived,
        unread = unread,
        shared = shared,
        tagNames = tagNames,
        dateAdded = dateAdded,
        dateModified = dateModified,
    )
}

fun BookmarkWithTags.toBookmark(): Bookmark {
    return Bookmark(
        id = id,
        url = url,
        title = title,
        description = description,
        websiteTitle = websiteTitle,
        websiteDescription = websiteDescription,
        isArchived = isArchived == 1L,
        unread = unread == 1L,
        shared = shared == 1L,
        tagNames = tagList?.split(",") ?: emptyList(),
        dateAdded = dateAdded,
        dateModified = dateModified,
    )
}