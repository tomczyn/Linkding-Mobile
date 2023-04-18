package com.tomczyn.linkding.data.local

import app.cash.sqldelight.coroutines.asFlow
import com.tomczyn.linkding.database.BookmarkEntity
import com.tomczyn.linkding.database.BookmarkWithTags
import com.tomczyn.linkding.database.LinkdingDatabase
import com.tomczyn.linkding.database.TagEntity
import kotlinx.coroutines.flow.first

class LinkdingDao(
    private val database: LinkdingDatabase
) {
    suspend fun getBookmarks(): List<BookmarkWithTags> = database.databaseQueries.bookmarkWithTags()
        .asFlow()
        .first()
        .executeAsList()

    suspend fun getTags(): List<TagEntity> = database.databaseQueries.allTags()
        .asFlow()
        .first()
        .executeAsList()

    suspend fun getBookmarksForTag(vararg names: String): List<BookmarkEntity> =
        database.databaseQueries
            .bookmarksForTags(names.toList())
            .asFlow()
            .first()
            .executeAsList()

    suspend fun saveTags(vararg tag: TagEntity) {
    }
}