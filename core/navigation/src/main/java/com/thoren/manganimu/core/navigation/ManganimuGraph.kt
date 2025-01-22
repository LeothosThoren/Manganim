package com.thoren.manganimu.core.navigation

import kotlinx.serialization.Serializable

@Serializable
sealed interface ManganimuGraph {
    @Serializable
    sealed class AnimeGraph : ManganimuGraph {
        @Serializable
        data object Dashboard : AnimeGraph()

        @Serializable
        data class Episodes(val id: Int) : AnimeGraph()
        data class AnimeInfo(val id: Int) : AnimeGraph()
    }

    @Serializable
    sealed class MangaGraph : ManganimuGraph {
        @Serializable
        data object Dashboard : MangaGraph()

        @Serializable
        data object Chapters : MangaGraph()
    }

    @Serializable
    sealed class MusicGraph : ManganimuGraph {
        @Serializable
        data object Dashboard : MusicGraph()

        @Serializable
        data object Player : MusicGraph()
    }

    @Serializable
    sealed class OptionGraph : ManganimuGraph {
        @Serializable
        data object Settings : OptionGraph()
    }

    companion object {
        @Transient
        val topLevelDestination: List<ManganimuGraph> = listOf(
            AnimeGraph.Dashboard,
            MangaGraph.Dashboard,
            MusicGraph.Dashboard,
            OptionGraph.Settings
        )
    }
}