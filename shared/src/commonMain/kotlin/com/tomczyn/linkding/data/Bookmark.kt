package com.tomczyn.linkding.data

import com.tomczyn.linkding.data.remote.BookmarkRemote
import com.tomczyn.linkding.database.BookmarkEntity

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

fun BookmarkEntity.toBookmark(): Bookmark {
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
        tagNames = tagNames.split(" "),
        dateAdded = dateAdded,
        dateModified = dateModified,
    )
}

fun Bookmark.toBookmarkEntity(): BookmarkEntity {
    return BookmarkEntity(
        id = id,
        url = url,
        title = title,
        description = description,
        websiteTitle = websiteTitle,
        websiteDescription = websiteDescription,
        isArchived = if (isArchived) 1L else 0L,
        unread = if (unread) 1L else 0L,
        shared = if (shared) 1L else 0L,
        tagNames = tagNames.joinToString(" "),
        dateAdded = dateAdded,
        dateModified = dateModified,
    )
}
