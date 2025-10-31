package com.thoren.manganimu.feature.anime.dashboard

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.thoren.manganimu.common.onFailure
import com.thoren.manganimu.common.onSuccess
import com.thoren.manganimu.core.models.AnimeFailure
import com.thoren.manganimu.domain.anime.usecases.GetAnimeRecommendationsUseCase
import com.thoren.manganimu.feature.anime.R
import com.thoren.manganimu.feature.anime.dashboard.model.DashboardUiState
import com.thoren.manganimu.feature.anime.dashboard.model.toCoverAnimeUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
internal class DashboardViewModel @Inject constructor(
    private val getAnimeRecommendationsUseCase: GetAnimeRecommendationsUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(buildDefaultUiState())
    val uiState: StateFlow<DashboardUiState> = _uiState.asStateFlow()


    init {
        getPopularAnimeList()
    }


    private fun getPopularAnimeList() {
        viewModelScope.launch {
            getAnimeRecommendationsUseCase.invoke()
                .onSuccess { animeList ->
                    _uiState.update { state ->
                        (state as DashboardUiState.Success).copy(popularAnime = animeList.map {
                            it.toCoverAnimeUiModel()
                        })
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
                        DashboardUiState.Error(message)
                    }
                }
        }
    }

    private fun buildDefaultUiState(): DashboardUiState {
        return DashboardUiState.Success(emptyList())
    }
}