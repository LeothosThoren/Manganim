@file:OptIn(InternalSerializationApi::class)

package com.thoren.manganimu.data.anime

import com.thoren.manganimu.common.IoDispatcher
import com.thoren.manganimu.common.ResultOf
import com.thoren.manganimu.common.mapFailure
import com.thoren.manganimu.common.mapSuccess
import com.thoren.manganimu.core.models.AnimeFailure
import com.thoren.manganimu.core.models.AnimeItem
import com.thoren.manganimu.core.network.extension.apiCall
import com.thoren.manganimu.core.network.networkdatasources.AnimeNetworkDataSource
import com.thoren.manganimu.data.anime.mappers.toAnimeFailure
import com.thoren.manganimu.data.anime.mappers.toAnimeItems
import com.thoren.manganimu.domain.anime.repositories.AnimeRecommendationsRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import kotlinx.serialization.InternalSerializationApi
import javax.inject.Inject

internal class AnimeRepositoryImpl @Inject constructor(
    private val animeNetworkDataSource: AnimeNetworkDataSource,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
) : AnimeRecommendationsRepository {

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
