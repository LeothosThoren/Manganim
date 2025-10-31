package com.thoren.manganimu.feature.anime.dashboard.model

import com.thoren.manganimu.core.models.AnimeItem

data class PopularAnimeUiModel(
    val id: Int,
    val title: String,
    val imageUrl: String?
)

fun AnimeItem.toCoverAnimeUiModel() = PopularAnimeUiModel(
    id = id,
    title = title,
    imageUrl = coverImage.extraLarge
)