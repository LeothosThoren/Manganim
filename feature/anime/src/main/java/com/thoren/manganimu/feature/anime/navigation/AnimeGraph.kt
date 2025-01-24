package com.thoren.manganimu.feature.anime.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.thoren.manganimu.core.navigation.ManganimuGraph
import com.thoren.manganimu.feature.anime.dashboard.screens.DashboardRoute
import com.thoren.manganimu.feature.anime.detail.screens.AnimeInfoRoute
import com.thoren.manganimu.feature.anime.episodeplayer.screens.EpisodePlayerRoute

/** Anime Graph **/
fun NavGraphBuilder.animeScreen(navController: NavController) {
    composable<ManganimuGraph.AnimeGraph.Dashboard> {
        DashboardRoute { animeId ->
            navController.navigateToAnimeInfo(animeId)
        }
    }

    composable<ManganimuGraph.AnimeGraph.AnimeInfo> { entry ->
        val id = entry.toRoute<ManganimuGraph.AnimeGraph.AnimeInfo>().id
        AnimeInfoRoute(id) { animeId, episodeId ->
            navController.navigateToAnimeEpisodesPlayer(animeId, episodeId)
        }
    }

    composable<ManganimuGraph.AnimeGraph.AnimeEpisodesPlayer> { entry ->
        val animeId = entry.toRoute<ManganimuGraph.AnimeGraph.AnimeEpisodesPlayer>().animeId
        val episodeId = entry.toRoute<ManganimuGraph.AnimeGraph.AnimeEpisodesPlayer>().episodeId
        EpisodePlayerRoute(animeId, episodeId)
    }
}