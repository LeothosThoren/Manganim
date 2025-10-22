package com.thoren.manganimu.core.network.networkdatasources

import com.thoren.manganimu.core.network.models.anime.AnimeDetailResponse
import com.thoren.manganimu.core.network.models.anime.AnimeEpisodeResponse
import com.thoren.manganimu.core.network.models.anime.PopularAnimeResponse
import com.thoren.manganimu.core.network.models.anime.recommendations.AnimeRecommendationsResponse
import com.thoren.manganimu.core.network.models.anime.stream.VideoStreamResponse

interface AnimeNetworkDataSource {

    suspend fun getPopularAnime(): PopularAnimeResponse // https://api.amvstr.me/api/v2/popular/all

    suspend fun getVideoStream(
        episodeId: String,
        episodeNbr: String
    ): VideoStreamResponse // https://api.amvstr.me/api/v2/stream

    suspend fun getAnimeEpisodes(id: String): AnimeEpisodeResponse // https://api.amvstr.me/api/v2/episode/id

    suspend fun getAnimeDetails(id: String): AnimeDetailResponse // https://api.amvstr.me/api/v2/infp/id

    suspend fun getRecentAnimeRecommendations(): AnimeRecommendationsResponse

}
