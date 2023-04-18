package com.tomczyn.linkding.data.remote

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TagsResponse(
    val count: Int,
    val next: String?,
    val previous: String?,
    val results: List<TagRemote>
)

@Serializable
data class TagRemote(
    val id: Long,
    val name: String,
    @SerialName("date_added")
    val dateAdded: String,
)
