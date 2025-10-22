@file:OptIn(InternalSerializationApi::class)

package com.thoren.manganimu.data.anime

import com.thoren.manganimu.common.IoDispatcher
import com.thoren.manganimu.common.ResultOf
import com.thoren.manganimu.common.mapFailure
import com.thoren.manganimu.common.mapSuccess
import com.thoren.manganimu.core.models.AnimeDetail
import com.thoren.manganimu.core.models.AnimeFailure
import com.thoren.manganimu.core.models.AnimeItem
import com.thoren.manganimu.core.models.AnimeStream
import com.thoren.manganimu.core.models.EpisodeItem
import com.thoren.manganimu.core.network.extension.apiCall
import com.thoren.manganimu.core.network.networkdatasources.AnimeNetworkDataSource
import com.thoren.manganimu.data.anime.mappers.toAnimeDetail
import com.thoren.manganimu.data.anime.mappers.toAnimeFailure
import com.thoren.manganimu.data.anime.mappers.toAnimeItems
import com.thoren.manganimu.data.anime.mappers.toAnimeStream
import com.thoren.manganimu.data.anime.mappers.toEpisodeItems
import com.thoren.manganimu.domain.anime.repositories.AnimeDetailsRepository
import com.thoren.manganimu.domain.anime.repositories.AnimeRecommendationsRepository
import com.thoren.manganimu.domain.anime.repositories.EpisodeRepository
import com.thoren.manganimu.domain.anime.repositories.PopularAnimeRepository
import com.thoren.manganimu.domain.anime.repositories.StreamAnimeRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import kotlinx.serialization.InternalSerializationApi
import javax.inject.Inject

internal class AnimeRepositoryImpl @Inject constructor(
    private val animeNetworkDataSource: AnimeNetworkDataSource,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
) : PopularAnimeRepository, EpisodeRepository, AnimeDetailsRepository, StreamAnimeRepository,
    AnimeRecommendationsRepository {

    override suspend fun getPopularAnime(): ResultOf<List<AnimeItem>, AnimeFailure> =
        withContext(ioDispatcher) {
            apiCall {
                animeNetworkDataSource.getPopularAnime()
            }.mapSuccess { popularAnimeResponses ->
                popularAnimeResponses.toAnimeItems()
            }
        }.mapFailure {
            it.toAnimeFailure()
        }

    override suspend fun getAnimeEpisodes(id: String): ResultOf<List<EpisodeItem>, AnimeFailure> =
        withContext(ioDispatcher) {
            apiCall {
                animeNetworkDataSource.getAnimeEpisodes(id)
            }.mapSuccess { animeEpisodeResponse ->
                animeEpisodeResponse.toEpisodeItems()
            }.mapFailure {
                it.toAnimeFailure()
            }
        }

    override suspend fun getAnimeDetails(id: String): ResultOf<AnimeDetail, AnimeFailure> =
        withContext(ioDispatcher) {
            apiCall {
                animeNetworkDataSource.getAnimeDetails(id)
            }.mapSuccess { animeDetailResponse ->
                animeDetailResponse.toAnimeDetail()
            }.mapFailure {
                it.toAnimeFailure()
            }
        }

    override suspend fun getStreamAnime(
        episodeId: String,
        episodeNbr: String
    ): ResultOf<AnimeStream, AnimeFailure> =
        withContext(ioDispatcher) {
            apiCall {
                animeNetworkDataSource.getVideoStream(episodeId, episodeNbr)
            }.mapSuccess { videoStreamResponse ->
                videoStreamResponse.toAnimeStream()
            }.mapFailure {
                it.toAnimeFailure()
            }
        }

    override suspend fun getAnimeRecommendations(): ResultOf<List<AnimeItem>, AnimeFailure> {
        return withContext(ioDispatcher) {
            apiCall {
                animeNetworkDataSource.getRecentAnimeRecommendations()
            }.mapSuccess { animeRecommendationsResponse ->
                animeRecommendationsResponse.data.map { it.toAnimeItems() }.flatten()
            }.mapFailure {
                it.toAnimeFailure()
            }
        }
    }
}
