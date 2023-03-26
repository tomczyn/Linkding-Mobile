package com.tomczyn.linkding.common

sealed interface Result {
    object Success : Result
    object Failure : Result
}