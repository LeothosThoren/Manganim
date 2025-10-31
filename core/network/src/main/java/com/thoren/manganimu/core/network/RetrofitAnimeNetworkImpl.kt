package com.thoren.manganimu.core.network

import android.util.Log
import com.thoren.manganimu.core.network.models.anime.recommendations.AnimeRecommendationsResponse
import com.thoren.manganimu.core.network.networkdatasources.AnimeNetworkDataSource
import com.thoren.manganimu.core.network.services.AnimeApiService
import jakarta.inject.Inject

internal class RetrofitAnimeNetworkImpl @Inject constructor(
    private val animeApiService: AnimeApiService
) : AnimeNetworkDataSource {

    override suspend fun getRecentAnimeRecommendations(): AnimeRecommendationsResponse {
        val response = animeApiService.getRecentAnimeRecommendations()
        Log.d("LOGGER_RetrofitAnimeNetworkImpl", "getRecentAnimeRecommendations: $response")
        return response
    }
}