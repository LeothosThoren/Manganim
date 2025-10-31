package com.thoren.manganimu.core.network.services

import com.thoren.manganimu.core.network.models.anime.recommendations.AnimeRecommendationsResponse
import retrofit2.http.GET

internal interface AnimeApiService {

    @GET("v4/recommendations/anime")
    suspend fun getRecentAnimeRecommendations(): AnimeRecommendationsResponse
}