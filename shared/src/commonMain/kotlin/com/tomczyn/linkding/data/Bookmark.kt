package com.tomczyn.linkding.data

@kotlinx.serialization.Serializable
data class Bookmark(
    val url: String,
    val title: String? = null,
    val description: String? = null,
    val tags: List<String> = emptyList(),
    val unread: Boolean = false
)