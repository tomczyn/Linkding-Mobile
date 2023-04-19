package com.tomczyn.linkding.data

import com.tomczyn.linkding.data.local.LocalDao
import com.tomczyn.linkding.data.remote.BookmarkRemote
import com.tomczyn.linkding.data.remote.LinkdingService
import com.tomczyn.linkding.data.remote.TagRemote
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class BookmarksRepository(
    private val dao: LocalDao,
    private val service: LinkdingService,
) {

    private val _bookmarks: MutableStateFlow<List<Bookmark>> = MutableStateFlow(emptyList())
    val bookmarks: StateFlow<List<Bookmark>> get() = _bookmarks

    private val _tags: MutableStateFlow<List<Tag>> = MutableStateFlow(emptyList())
    val tags: StateFlow<List<Tag>> get() = _tags

    suspend fun refreshBookmarks() {
        _bookmarks.emit(dao.getBookmarks().map { it.toBookmark() })
        var nextUrl: String? = null
        var error = false
        val bookmarks = mutableListOf<Bookmark>()
        do {
            service.getBookmarks(nextUrl)
                .onRight { response ->
                    bookmarks.addAll(response.results.map(BookmarkRemote::toBookmark))
                    nextUrl = response.next
                }
                .onLeft {
                    nextUrl = null
                    error = true
                }
        } while (nextUrl != null)
        _bookmarks.emit(bookmarks)
        if (!error) dao.removeAllBookmarks()
        dao.saveBookmark(*bookmarks.map(Bookmark::toBookmarkEntity).toTypedArray())
    }

    suspend fun refreshTags() {
        _tags.emit(dao.getTags().map { it.toTag() })
        var nextUrl: String? = null
        var error = false
        val tags = mutableListOf<Tag>()
        do {
            service.getTags(nextUrl)
                .onRight { response ->
                    tags.addAll(response.results.map(TagRemote::toTag))
                    nextUrl = response.next
                }
                .onLeft {
                    nextUrl = null
                    error = true
                }
        } while (nextUrl != null)
        _tags.emit(tags)
        if (!error) dao.removeAllTags()
        dao.saveTags(*tags.map(Tag::toTagEntity).toTypedArray())
    }
}
