package com.thoren.manganimu.domain.anime

import com.thoren.manganimu.common.ResultOf
import com.thoren.manganimu.core.models.AnimeFailure
import com.thoren.manganimu.domain.anime.repositories.EpisodeRepository
import com.thoren.manganimu.domain.anime.usecases.GetAnimeEpisodeUseCaseImpl
import kotlinx.coroutines.test.runTest
import org.junit.Test

class GetAnimeEpisodeUseCaseTest {

    @Test
    fun `when execute GetAnimeEpisodeUseCase should return success`() = runTest {
        // Given
        val repository = EpisodeRepository {
            ResultOf.Success(emptyList())
        }

        // When
        val result = getAnimeEpisodeUseCase(repository).invoke("1")

        // Then
        assert(result is ResultOf.Success)
    }

    @Test
    fun `when execute GetAnimeEpisodeUseCase should return error`() = runTest {
        // Given
        val repository = EpisodeRepository {
            ResultOf.Failure(AnimeFailure.Technical)
        }
        // When
        val result = getAnimeEpisodeUseCase(repository).invoke("1")

        // Then
        assert(result is ResultOf.Failure)
    }

    private fun getAnimeEpisodeUseCase(episodeRepository: EpisodeRepository) =
        GetAnimeEpisodeUseCaseImpl(episodeRepository)
}