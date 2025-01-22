package com.thoren.manganimu.feature.anime.detail.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.thoren.manganimu.core.ui.common.SpaceSize
import com.thoren.manganimu.feature.anime.detail.DetailViewModel
import com.thoren.manganimu.feature.anime.detail.models.AnimeDetailUiState
import com.thoren.manganimu.feature.anime.detail.models.EpisodeItemUiModel

@Composable
internal fun AnimeInfoRoute(
    animeId: Int,
    viewModel: DetailViewModel = hiltViewModel<DetailViewModel>(key = animeId.toString()),
    modifier: Modifier = Modifier,
    onEpisodeClick: (String, String) -> Unit
) {
    val uiState = viewModel.uiState.collectAsStateWithLifecycle()

    AnimeInfoScreen(
        state = uiState.value,
        modifier = modifier,
        onEpisodeClick = onEpisodeClick
    )
}

@Composable
internal fun AnimeInfoScreen(
    state: AnimeDetailUiState,
    modifier: Modifier = Modifier,
    onEpisodeClick: (String, String) -> Unit
) {
    Scaffold { innerPadding ->
        when (state) {
            is AnimeDetailUiState.Loading -> LoadingScreen(
                modifier = modifier.padding(innerPadding),
            )

            is AnimeDetailUiState.Success -> {
                AnimeInfoScreenContent(
                    state = state,
                    modifier = modifier.padding(innerPadding),
                    onEpisodeClick = onEpisodeClick
                )
            }

            is AnimeDetailUiState.Error -> ErrorScreen(
                error = stringResource(state.message),
                modifier = modifier.padding(innerPadding)
            )
        }
    }
}

@Composable
private fun LoadingScreen(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(text = "Loading...")
    }
}

@Composable
private fun ErrorScreen(error: String, modifier: Modifier = Modifier) {
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(text = "Error: $error")
    }
}

@Composable
private fun AnimeInfoScreenContent(
    state: AnimeDetailUiState.Success,
    modifier: Modifier = Modifier,
    onEpisodeClick: (String, String) -> Unit
) {
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Text(text = "Anime Screen for animeId: ${state.animeDetail.id}")
        Text(text = "Title: ${state.animeDetail.title}")
        EpisodeItemList(
            animeId = state.animeDetail.idProvider,
            episodes = state.episodes,
            onItemClick = onEpisodeClick
        )
    }
}

@Composable
private fun EpisodeItemList(
    animeId: String,
    episodes: List<EpisodeItemUiModel>,
    modifier: Modifier = Modifier,
    onItemClick: (String, String) -> Unit
) {
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        LazyColumn {
            items(episodes) { episode ->
                EpisodeItem(
                    animeId = animeId,
                    episode = episode,
                    modifier = modifier,
                    onEpisodeClick = onItemClick
                )
            }
        }
    }
}

@Composable
private fun EpisodeItem(
    animeId: String,
    episode: EpisodeItemUiModel,
    modifier: Modifier = Modifier,
    onEpisodeClick: (String, String) -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(SpaceSize.medium)
            .clickable { onEpisodeClick(animeId, episode.id) },
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Text(text = "Episode: ${episode.title}")
        Text(text = "Description: ${episode.description}")
    }
}