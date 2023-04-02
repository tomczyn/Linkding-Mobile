package com.tomczyn.linkding

import com.tomczyn.linkding.common.Response
import com.tomczyn.linkding.common.delete
import com.tomczyn.linkding.common.get
import com.tomczyn.linkding.common.post
import com.tomczyn.linkding.common.put
import com.tomczyn.linkding.data.ApiError
import com.tomczyn.linkding.data.Bookmark
import com.tomczyn.linkding.data.BookmarksResponse
import com.tomczyn.linkding.data.TagsResponse
import io.ktor.client.*
import io.ktor.client.request.*

class LinkdingRepository(
    private val httpClient: HttpClient,
    private val settings: Settings
) {

    private val baseUrl: String
        get() = settings.getString("host")!!.run {
            if (last().toString() == "/") {
                removeSuffix("/")
            } else {
                this
            }
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

    suspend fun createBookmark(bookmark: Bookmark): Response<ApiError, Bookmark> {
        val url = "$baseUrl/api/bookmarks/"
        return httpClient.post(url, bookmark)
    }

    suspend fun getBookmark(id: Int): Response<ApiError, Bookmark> {
        val url = "$baseUrl/api/bookmarks/$id/"
        return httpClient.get(url)
    }

    suspend fun updateBookmark(id: Int, bookmark: Bookmark): Response<ApiError, Bookmark> {
        val url = "$baseUrl/api/bookmarks/$id/"
        return httpClient.put(url, bookmark)
    }

    suspend fun deleteBookmark(id: Int): Response<ApiError, Unit> {
        val url = "$baseUrl/api/bookmarks/$id/"
        return httpClient.delete(url)
    }

    suspend fun getTags(): Response<ApiError, TagsResponse> {
        val url = "$baseUrl/api/tags/"
        return httpClient.get(url)
    }
}
