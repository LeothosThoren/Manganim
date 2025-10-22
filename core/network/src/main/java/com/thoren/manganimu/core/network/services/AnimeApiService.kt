package com.thoren.manganimu.core.network.services

import com.thoren.manganimu.core.network.models.anime.AnimeDetailResponse
import com.thoren.manganimu.core.network.models.anime.AnimeEpisodeResponse
import com.thoren.manganimu.core.network.models.anime.PopularAnimeResponse
import com.thoren.manganimu.core.network.models.anime.recommendations.AnimeRecommendationsResponse
import com.thoren.manganimu.core.network.models.anime.stream.VideoStreamResponse
import retrofit2.http.GET
import retrofit2.http.Path

internal interface AnimeApiService {

    @GET("api/v2/popular")
    suspend fun getPopularAnime(): PopularAnimeResponse

    @GET("api/v2/episodes/{id}")
    suspend fun getAnimeEpisodes(@Path("id") id: String): AnimeEpisodeResponse

    @GET("api/v2/info/{id}")
    suspend fun getAnimeDetails(@Path("id") id: String): AnimeDetailResponse

    @GET("api/v2/stream/{id}/{ep}")
    suspend fun getVideoStream(
        @Path("id") episodeId: String,
        @Path("ep") episodeNbr: String
    ): VideoStreamResponse

    @GET("v4/recommendations/anime")
    suspend fun getRecentAnimeRecommendations(): AnimeRecommendationsResponse
}