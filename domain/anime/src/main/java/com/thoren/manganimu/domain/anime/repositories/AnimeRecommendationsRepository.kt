package com.thoren.manganimu.domain.anime.repositories

import com.thoren.manganimu.common.ResultOf
import com.thoren.manganimu.core.models.AnimeFailure
import com.thoren.manganimu.core.models.AnimeItem

interface AnimeRecommendationsRepository {
    suspend fun getAnimeRecommendations(): ResultOf<List<AnimeItem>, AnimeFailure>
}