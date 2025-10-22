package com.thoren.manganimu.feature.anime.dashboard.screens

import PreviewTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.thoren.manganimu.core.ui.common.SpaceSize
import com.thoren.manganimu.core.ui.component.BasicAnimeTile
import com.thoren.manganimu.feature.anime.dashboard.DashboardViewModel
import com.thoren.manganimu.feature.anime.dashboard.model.DashboardUiState
import com.thoren.manganimu.feature.anime.dashboard.model.PopularAnimeUiModel
import kotlin.random.Random

@Composable
internal fun DashboardRoute(
    modifier: Modifier = Modifier,
    viewModel: DashboardViewModel = hiltViewModel<DashboardViewModel>(),
    onClickItem: (Int) -> Unit
) {
    val state = viewModel.uiState.collectAsStateWithLifecycle()

    DashboardScreen(modifier = modifier, state = state.value, onClickItem = onClickItem)
}

@Composable
internal fun DashboardScreen(
    modifier: Modifier = Modifier,
    state: DashboardUiState,
    onClickItem: (Int) -> Unit
) {
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        when (state) {
            is DashboardUiState.Loading -> {
                Text(text = "Loading...")
            }

            is DashboardUiState.Success -> {
                LazyVerticalGrid(
                    columns = GridCells.Fixed(2),
                    verticalArrangement = Arrangement.spacedBy(SpaceSize.small),
                    horizontalArrangement = Arrangement.spacedBy(SpaceSize.small),
                    contentPadding = PaddingValues(SpaceSize.medium)
                ) {
                    items(state.popularAnime) { anime ->
                        BasicAnimeTile(
                            title = anime.title,
                            imageUrl = anime.imageUrl,
                            onClick = { onClickItem(anime.id) }
                        )
                    }
                }
            }

            is DashboardUiState.Error -> {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Text(text = "Error: ${stringResource(state.message)}")
                }
            }
        }
    }
}


@Composable
@PreviewLightDark
private fun DashboardScreenPreview() {
    PreviewTheme {
        DashboardScreen(
            state = DashboardUiState.Success(
                popularAnime = (1..10)
                    .map {
                        PopularAnimeUiModel(
                            id = it,
                            title = Random.nextLong().toString(),
                            imageUrl = "https://cdn.myanimelist.net/images/anime/13/17405.jpg"
                        )
                    },
            ),
            onClickItem = { }
        )
    }
}