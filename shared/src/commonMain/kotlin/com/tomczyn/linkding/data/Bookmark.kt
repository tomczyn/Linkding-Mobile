package com.tomczyn.linkding.data

import com.tomczyn.linkding.data.remote.BookmarkRemote
import com.tomczyn.linkding.database.BookmarkEntity
import io.ktor.http.URLBuilder
import io.ktor.http.takeFrom

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
) {

    val formattedDateAdded: String
        get() {
            try {
                with(dateAdded) {
                    val year = substring(0, 4)
                    val month = substring(5, 7)
                    val day = substring(8, 10)
                    val hour = substring(11, 13)
                    val minute = substring(14, 16)
                    return "$day/$month/$year, $hour:$minute"
                }
            } catch (error: Throwable) {
                return dateAdded
            }
        }

    val urlHost: String
        get() {
            val parsedUrl = URLBuilder().apply { takeFrom(url) }.build()
            val host = parsedUrl.host

            return if (host.startsWith("www.")) {
                host.removePrefix("www.")
            } else {
                host
            }
        }
}

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
