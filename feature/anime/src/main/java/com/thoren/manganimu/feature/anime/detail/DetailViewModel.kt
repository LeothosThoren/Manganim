package com.thoren.manganimu.feature.anime.detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.thoren.manganimu.common.onFailure
import com.thoren.manganimu.common.onSuccess
import com.thoren.manganimu.core.models.AnimeFailure
import com.thoren.manganimu.core.models.AnimeItem
import com.thoren.manganimu.core.navigation.ManganimuGraph
import com.thoren.manganimu.domain.anime.usecases.GetAnimeDetailsUseCase
import com.thoren.manganimu.domain.anime.usecases.GetAnimeEpisodeUseCase
import com.thoren.manganimu.feature.anime.R
import com.thoren.manganimu.feature.anime.detail.models.AnimeDetailUiModel
import com.thoren.manganimu.feature.anime.detail.models.AnimeDetailUiState
import com.thoren.manganimu.feature.anime.detail.models.toAnimeDetailUiModel
import com.thoren.manganimu.feature.anime.detail.models.toEpisodeItemUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
internal class DetailViewModel @Inject constructor(
    handle: SavedStateHandle,
    private val getAnimeDetailsUseCase: GetAnimeDetailsUseCase,
    private val getAnimeEpisodesUseCase: GetAnimeEpisodeUseCase
) : ViewModel() {

    private val episode = handle.toRoute<ManganimuGraph.AnimeGraph.AnimeInfo>()

    private val _uiState = MutableStateFlow<AnimeDetailUiState>(buildDefaultUiState())
    val uiState: StateFlow<AnimeDetailUiState> = _uiState.asStateFlow()

    init {
        loadScreen()
    }

    private fun loadScreen() {
        viewModelScope.launch {
            awaitAll(async {
                loadDetail(episode.id.toString())
            }, async {
                loadEpisodes(episode.id.toString())
            })
        }
    }

    private suspend fun loadDetail(animeId: String) {
        getAnimeDetailsUseCase.invoke(animeId)
            .onSuccess { animeDetail ->
                _uiState.update { state ->
                    (state as AnimeDetailUiState.Success).copy(
                        animeDetail = animeDetail.toAnimeDetailUiModel()
                    )
                }
            }.onFailure { failure ->
                _uiState.update {
                    val message = when (failure) {
                        is AnimeFailure.BadRequest -> R.string.feature_anime_failure_bad_request
                        is AnimeFailure.NotFound -> R.string.feature_anime_failure_not_found
                        is AnimeFailure.TooManyRequests -> R.string.feature_anime_failure_many_requests
                        is AnimeFailure.ServerError -> R.string.feature_anime_failure_server_error
                        else -> R.string.feature_anime_failure_unknown
                    }
                    AnimeDetailUiState.Error(message)
                }
            }
    }

    private suspend fun loadEpisodes(animeId: String) {
        getAnimeEpisodesUseCase.invoke(animeId)
            .onSuccess { episodes ->
                _uiState.update { state ->
                    (state as AnimeDetailUiState.Success).copy(
                        episodes = episodes.map { it.toEpisodeItemUiModel() }
                    )
                }
            }.onFailure { failure ->
                _uiState.update {
                    val message = when (failure) {
                        is AnimeFailure.BadRequest -> R.string.feature_anime_failure_bad_request
                        is AnimeFailure.NotFound -> R.string.feature_anime_failure_not_found
                        is AnimeFailure.TooManyRequests -> R.string.feature_anime_failure_many_requests
                        is AnimeFailure.ServerError -> R.string.feature_anime_failure_server_error
                        else -> R.string.feature_anime_failure_unknown
                    }
                    AnimeDetailUiState.Error(message)
                }
            }
    }

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
