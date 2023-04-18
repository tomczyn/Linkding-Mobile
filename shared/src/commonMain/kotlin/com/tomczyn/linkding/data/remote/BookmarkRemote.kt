package com.tomczyn.linkding.data.remote

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class BookmarkRemote(
    val id: Long,
    val url: String,
    val title: String?,
    val description: String?,
    @SerialName("website_title")
    val websiteTitle: String?,
    @SerialName("website_description")
    val websiteDescription: String?,
    @SerialName("is_archived")
    val isArchived: Boolean,
    val unread: Boolean,
    val shared: Boolean,
    @SerialName("tag_names")
    val tagNames: List<String>,
    @SerialName("date_added")
    val dateAdded: String,
    @SerialName("date_modified")
    val dateModified: String,
)
