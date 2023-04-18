package com.tomczyn.linkding.data.remote

import kotlinx.serialization.Serializable

@Serializable
data class ApiError(
    val detail: String
)
