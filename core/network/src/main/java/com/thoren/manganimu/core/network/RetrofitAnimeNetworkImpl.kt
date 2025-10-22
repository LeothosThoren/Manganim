package com.thoren.manganimu.core.network

import android.util.Log
import com.thoren.manganimu.core.network.models.anime.AnimeDetailResponse
import com.thoren.manganimu.core.network.models.anime.AnimeEpisodeResponse
import com.thoren.manganimu.core.network.models.anime.PopularAnimeResponse
import com.thoren.manganimu.core.network.models.anime.recommendations.AnimeRecommendationsResponse
import com.thoren.manganimu.core.network.models.anime.stream.VideoStreamResponse
import com.thoren.manganimu.core.network.networkdatasources.AnimeNetworkDataSource
import com.thoren.manganimu.core.network.services.AnimeApiService
import jakarta.inject.Inject

internal class RetrofitAnimeNetworkImpl @Inject constructor(
    private val animeApiService: AnimeApiService
) : AnimeNetworkDataSource {
    override suspend fun getPopularAnime(): PopularAnimeResponse {
        val response = animeApiService.getPopularAnime()
        Log.d("LOGGER_RetrofitAnimeNetworkImpl", "getPopularAnime: $response")
        return response
    }

    override suspend fun getVideoStream(
        episodeId: String,
        episodeNbr: String
    ): VideoStreamResponse {
        val response = animeApiService.getVideoStream(
            episodeId = episodeId,
            episodeNbr = episodeNbr
        )
        Log.d("LOGGER_RetrofitAnimeNetworkImpl", "getVideoStream: $response")
        return response
    }

    override suspend fun getAnimeEpisodes(id: String): AnimeEpisodeResponse {
        val response = animeApiService.getAnimeEpisodes(id)
        Log.d("LOGGER_RetrofitAnimeNetworkImpl", "getAnimeEpisodes: $response")
        return response
    }

    override suspend fun getAnimeDetails(id: String): AnimeDetailResponse {
        val response = animeApiService.getAnimeDetails(id)
        Log.d("LOGGER_RetrofitAnimeNetworkImpl", "getAnimeDetails: $response")
        return response
    }

    override suspend fun getRecentAnimeRecommendations(): AnimeRecommendationsResponse {
        val response = animeApiService.getRecentAnimeRecommendations()
        Log.d("LOGGER_RetrofitAnimeNetworkImpl", "getRecentAnimeRecommendations: $response")
        return response
    }
}