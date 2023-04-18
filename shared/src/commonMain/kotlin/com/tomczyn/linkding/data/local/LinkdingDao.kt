package com.tomczyn.linkding.data.local

import app.cash.sqldelight.coroutines.asFlow
import com.tomczyn.linkding.database.BookmarkEntity
import com.tomczyn.linkding.database.LinkdingDatabase
import com.tomczyn.linkding.database.TagEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.withContext

class LinkdingDao(
    private val database: LinkdingDatabase
) {
    suspend fun getBookmarks(): List<BookmarkEntity> = database.databaseQueries.selectAllBookmarks()
        .asFlow()
        .first()
        .executeAsList()

    suspend fun saveBookmark(vararg bookmarks: BookmarkEntity) {
        withContext(Dispatchers.Default) {
            bookmarks.forEach { bookmark -> database.databaseQueries.insertBookmark(bookmark) }
        }
    }

    suspend fun removeAllBookmarks() {
        withContext(Dispatchers.Default) {
            database.databaseQueries.removeAllBookmarks()
        }
    }

    suspend fun getTags(): List<TagEntity> = database.databaseQueries.selectAllTags()
        .asFlow()
        .first()
        .executeAsList()

    suspend fun saveTags(vararg tags: TagEntity) {
        withContext(Dispatchers.Default) {
            tags.forEach { tag -> database.databaseQueries.insertTag(tag) }
        }
    }

    suspend fun removeAllTags() {
        withContext(Dispatchers.Default) {
            database.databaseQueries.removeAllTags()
        }
    }
}