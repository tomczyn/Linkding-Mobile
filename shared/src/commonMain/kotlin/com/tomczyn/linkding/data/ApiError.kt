package com.tomczyn.linkding.data

import kotlinx.serialization.Serializable

@Serializable
data class ApiError(
    val detail: String
)