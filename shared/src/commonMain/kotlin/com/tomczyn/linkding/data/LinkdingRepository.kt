package com.tomczyn.linkding.data

import co.touchlab.kermit.Logger
import com.tomczyn.linkding.data.local.LinkdingDao
import com.tomczyn.linkding.data.remote.LinkdingService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class LinkdingRepository(
    private val dao: LinkdingDao,
    private val service: LinkdingService,
) {

    fun getBookmarks(): Flow<List<Bookmark>> = flow {
        emit(dao.getBookmarks().map { it.toBookmark() })
    }

    fun getTags(): Flow<List<Tag>> = flow {
        val fromLocal = dao.getTags().map { it.toTag() }
        emit(fromLocal)
        Logger.d { "getTags: Displaying tags from local: ${fromLocal.joinToString(", ") { it.name }}" }
        service.getTags().onRight { response ->
            Logger.d { "getTags: Fetched remote tags, size: ${response.results.size}, next: ${response.next}" }
            val tags = response.results.map { it.toTag() }
            emit(tags)
            dao.saveTags(*tags.map { it.toTagEntity() }.toTypedArray())
        }.onLeft {
            Logger.e { "getTags: Error while loading tags from remote $it" }
        }
    }

    suspend fun getBookmarksForTag(vararg names: String): List<Bookmark> {
        TODO()
    }
}
