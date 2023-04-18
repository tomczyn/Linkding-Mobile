package com.tomczyn.linkding.data.remote

import kotlinx.serialization.Serializable

@Serializable
data class BookmarkRemote(
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
