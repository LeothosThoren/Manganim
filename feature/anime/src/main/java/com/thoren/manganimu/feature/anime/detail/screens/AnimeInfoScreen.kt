package com.thoren.manganimu.feature.anime.detail.screens

import PreviewTheme
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.thoren.manganimu.core.models.AnimeItem
import com.thoren.manganimu.core.ui.common.SpaceSize
import com.thoren.manganimu.feature.anime.detail.DetailViewModel
import com.thoren.manganimu.feature.anime.detail.models.AnimeDetailUiModel
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
        state = fakeMetadataState(),
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
    Scaffold(Modifier.padding(horizontal = SpaceSize.medium)) { innerPadding ->
        when (state) {
            is AnimeDetailUiState.Loading -> LoadingScreen(
                modifier = modifier.padding(innerPadding),
            )

            is AnimeDetailUiState.Success -> {
                EpisodeItemList(
                    animeId = state.animeDetail.id.toString(),
                    episodes = state.episodes,
                    modifier = modifier.padding(innerPadding),
                    onItemClick = onEpisodeClick
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
private fun EpisodeItemList(
    animeId: String,
    episodes: List<EpisodeItemUiModel>,
    modifier: Modifier = Modifier,
    onItemClick: (String, String) -> Unit
) {
    Box(
        modifier = modifier
            .fillMaxSize()
    ) {
        LazyColumn() {
            item {
                Box(
                    modifier
                        .fillMaxWidth()
                        .height(270.dp)
                        .clip(RoundedCornerShape(SpaceSize.small))
                        .background(color = androidx.compose.ui.graphics.Color.Gray),
                    contentAlignment = Alignment.Center
                ) {
                    Text(text = "Anime Cover Image Placeholder")
                }
                Spacer(Modifier.size(SpaceSize.xlarge))
            }
            item {
                Text(
                    text = "Episodes",
                    style = MaterialTheme.typography.headlineSmall,

                    )
            }
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
            .clickable { onEpisodeClick(animeId, episode.id) },
        verticalArrangement = Arrangement.spacedBy(SpaceSize.small)
    ) {
        Text(text = "Episode: ${episode.title}")
        Text(text = "Description: ${episode.description}")
    }
}

@PreviewLightDark
@Composable
private fun AnimeInfoScreenPreview() {
    PreviewTheme {
        AnimeInfoScreen(
            state = AnimeDetailUiState.Success(
                animeDetail = AnimeDetailUiModel(
                    id = 1,
                    title = "Sample Anime",
                    idProvider = "sample-anime-001",
                    episodes = 12,
                    coverImage = AnimeItem.CoverImage(
                        extraLarge = "",
                        medium = "",
                        large = ""
                    ),
                    description = "This is a sample anime description.",
                    year = 2024
                ),
                episodes = List(5) { index ->
                    EpisodeItemUiModel(
                        id = "ep-${index + 1}",
                        title = "Episode ${index + 1}",
                        description = "Description for episode ${index + 1}",
                        number = 1,
                        image = ""
                    )
                }
            ), onEpisodeClick = { _, _ -> })
    }
}

private fun fakeMetadataState(): AnimeDetailUiState.Success {
    return AnimeDetailUiState.Success(
        animeDetail = AnimeDetailUiModel(
            id = 1,
            title = "Sample Anime",
            idProvider = "sample-anime-001",
            episodes = 12,
            coverImage = AnimeItem.CoverImage(
                extraLarge = "",
                medium = "",
                large = ""
            ),
            description = "This is a sample anime description.",
            year = 2024
        ),
        episodes = List(10) { index ->
            EpisodeItemUiModel(
                id = "ep-${index + 1}",
                title = "Episode ${index + 1}",
                description = "Description for episode ${index + 1}",
                number = 1,
                image = ""
            )
        }
    )
}