package com.thoren.manganimu.feature.anime.navigation

import androidx.navigation.NavController
import androidx.navigation.NavOptions
import com.thoren.manganimu.core.navigation.ManganimuGraph

fun NavController.navigateToAnime(navOptions: NavOptions) =
    navigate(route = ManganimuGraph.AnimeGraph.Dashboard, navOptions)

fun NavController.navigateToAnimeInfo(animeId: Int) =
    navigate(route = ManganimuGraph.AnimeGraph.AnimeInfo(animeId))

fun NavController.navigateToAnimeEpisodesPlayer(animeId: String, episodeId: String) =
    navigate(route = ManganimuGraph.AnimeGraph.AnimeEpisodesPlayer(animeId, episodeId))

