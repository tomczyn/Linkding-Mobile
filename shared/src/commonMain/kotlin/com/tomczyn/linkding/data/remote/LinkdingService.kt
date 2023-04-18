package com.tomczyn.linkding.data.remote

import co.touchlab.kermit.Logger
import com.tomczyn.linkding.LinkdingSettings
import com.tomczyn.linkding.LinkdingSettings.HOST
import com.tomczyn.linkding.Settings
import com.tomczyn.linkding.common.Response
import com.tomczyn.linkding.common.delete
import com.tomczyn.linkding.common.get
import com.tomczyn.linkding.common.post
import com.tomczyn.linkding.common.put
import com.tomczyn.linkding.getString
import io.ktor.client.HttpClient
import io.ktor.client.request.parameter

class LinkdingService(
    private val httpClient: HttpClient,
    private val settings: Settings
) {

    private val baseUrl: String by lazy {
        val value = settings.getString(HOST)?.run {
            if (last().toString() == "/") {
                removeSuffix("/")
            } else {
                this
            }
        } ?: ""
        value
    }

    suspend fun getBookmarks(
        search: String? = null,
        tags: List<String>? = null,
        untagged: Boolean? = null,
        limit: Int? = null,
        offset: Int? = null
    ): Response<ApiError, BookmarksResponse> {
        val url = "$baseUrl/api/bookmarks/"
        return httpClient.get(url) {
            parameter("search", search)
            parameter("tags", tags?.joinToString(","))
            parameter("untagged", untagged)
            parameter("limit", limit)
            parameter("offset", offset)
        }
    }

    suspend fun createBookmark(bookmark: BookmarkRemote): Response<ApiError, BookmarkRemote> {
        val url = "$baseUrl/api/bookmarks/"
        return httpClient.post(url, bookmark)
    }

    suspend fun getBookmark(id: Int): Response<ApiError, BookmarkRemote> {
        val url = "$baseUrl/api/bookmarks/$id/"
        return httpClient.get(url)
    }

    suspend fun updateBookmark(
        id: Int,
        bookmark: BookmarkRemote
    ): Response<ApiError, BookmarkRemote> {
        val url = "$baseUrl/api/bookmarks/$id/"
        return httpClient.put(url, bookmark)
    }

    suspend fun deleteBookmark(id: Int): Response<ApiError, Unit> {
        val url = "$baseUrl/api/bookmarks/$id/"
        return httpClient.delete(url)
    }

    suspend fun getTags(url: String? = null): Response<ApiError, TagsResponse> {
        return httpClient.get(url ?: "$baseUrl/api/tags/")
    }
}
