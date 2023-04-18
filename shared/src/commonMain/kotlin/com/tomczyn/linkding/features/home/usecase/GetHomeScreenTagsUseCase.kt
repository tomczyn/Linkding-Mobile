package com.tomczyn.linkding.features.home.usecase

import co.touchlab.kermit.Logger
import com.tomczyn.linkding.data.LinkdingRepository
import com.tomczyn.linkding.features.home.HomeScreenTag
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.zip

class GetHomeScreenTagsUseCase(
    private val repo: LinkdingRepository
) : () -> Flow<List<HomeScreenTag>> {
    override fun invoke(): Flow<List<HomeScreenTag>> {
        return combine(
            repo.tags,
            repo.bookmarks
        ) { tags, bookmarks ->
            tags.map { tag ->
                HomeScreenTag(
                    name = tag.name,
                    numberOfBookmarks = bookmarks.count { it.tagNames.contains(tag.name) })
            }.filter { it.numberOfBookmarks != 0 }
        }
    }
}
