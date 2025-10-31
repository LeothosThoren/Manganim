package com.thoren.manganimu.core.network.networkdatasources

import com.thoren.manganimu.core.network.models.anime.recommendations.AnimeRecommendationsResponse

interface AnimeNetworkDataSource {

    suspend fun getRecentAnimeRecommendations(): AnimeRecommendationsResponse

}
