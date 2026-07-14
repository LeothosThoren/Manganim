package com.thoren.manganimu.feature.anime.detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.navigation.toRoute
import com.thoren.manganimu.core.models.AnimeItem
import com.thoren.manganimu.core.navigation.ManganimuGraph
import com.thoren.manganimu.feature.anime.detail.models.AnimeDetailUiModel
import com.thoren.manganimu.feature.anime.detail.models.AnimeDetailUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
internal class DetailViewModel @Inject constructor(
    handle: SavedStateHandle,
) : ViewModel() {

    private val episode = handle.toRoute<ManganimuGraph.AnimeGraph.AnimeInfo>()

    private val _uiState = MutableStateFlow(buildDefaultUiState())
    val uiState: StateFlow<AnimeDetailUiState> = _uiState.asStateFlow()

    private fun buildDefaultUiState(): AnimeDetailUiState =
        AnimeDetailUiState.Success(
            animeDetail = AnimeDetailUiModel(
                id = 0,
                title = "",
                idProvider = "",
                episodes = 0,
                coverImage = AnimeItem.CoverImage("", "", ""),
                description = "",
                year = 0
            ),
            episodes = emptyList()
        )
}
