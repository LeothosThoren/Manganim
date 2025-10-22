package com.thoren.manganimu.domain.anime.usecases

import com.thoren.manganimu.common.ResultOf
import com.thoren.manganimu.core.models.AnimeFailure
import com.thoren.manganimu.core.models.AnimeItem
import com.thoren.manganimu.domain.anime.repositories.AnimeRecommendationsRepository
import javax.inject.Inject

fun interface GetAnimeRecommendationsUseCase {
    suspend operator fun invoke(): ResultOf<List<AnimeItem>, AnimeFailure>
}

internal class GetAnimeRecommendationsUseCaseImpl @Inject constructor(
    private val animeRecommendationsRepository: AnimeRecommendationsRepository
) : GetAnimeRecommendationsUseCase {
    override suspend fun invoke(): ResultOf<List<AnimeItem>, AnimeFailure> =
        animeRecommendationsRepository.getAnimeRecommendations()
}