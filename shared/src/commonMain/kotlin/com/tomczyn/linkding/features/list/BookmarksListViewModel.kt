package com.tomczyn.linkding.features.list

import com.rickclephas.kmm.viewmodel.KMMViewModel
import com.rickclephas.kmm.viewmodel.MutableStateFlow
import com.rickclephas.kmm.viewmodel.coroutineScope
import com.rickclephas.kmp.nativecoroutines.NativeCoroutinesState
import com.tomczyn.linkding.common.Launched
import com.tomczyn.linkding.common.stateInMerge
import com.tomczyn.linkding.data.BookmarksRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class BookmarksListViewModel(
    val tag: String
) : KMMViewModel(), KoinComponent {

    private val repo: BookmarksRepository by inject()

    private val _state: MutableStateFlow<BookmarksListState> =
        MutableStateFlow(viewModelScope, BookmarksListState())
            .stateInMerge(
                viewModelScope.coroutineScope,
                Launched.WhileSubscribed(5_000),
                {
                    repo.bookmarks
                        .map { it.filter { bookmark -> bookmark.tagNames.contains(tag) } }
                        .onEachToState { bookmarks, state -> state.copy(bookmarks = bookmarks) }
                }
            )

    @NativeCoroutinesState
    val state: StateFlow<BookmarksListState> get() = _state
}
