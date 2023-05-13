package com.tomczyn.linkding.features.list

sealed interface BookmarksListFilter {
    object All : BookmarksListFilter
    object Unread : BookmarksListFilter
    object Untagged : BookmarksListFilter
    object Archived : BookmarksListFilter
    data class TagName(val tag: String) : BookmarksListFilter
}
