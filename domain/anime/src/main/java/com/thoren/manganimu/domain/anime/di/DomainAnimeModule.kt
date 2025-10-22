package com.thoren.manganimu.domain.anime.di

import com.thoren.manganimu.domain.anime.usecases.GetAnimeDetailsUseCase
import com.thoren.manganimu.domain.anime.usecases.GetAnimeDetailsUseCaseImpl
import com.thoren.manganimu.domain.anime.usecases.GetAnimeEpisodeUseCase
import com.thoren.manganimu.domain.anime.usecases.GetAnimeEpisodeUseCaseImpl
import com.thoren.manganimu.domain.anime.usecases.GetAnimeRecommendationsUseCase
import com.thoren.manganimu.domain.anime.usecases.GetAnimeRecommendationsUseCaseImpl
import com.thoren.manganimu.domain.anime.usecases.GetAnimeStreamUseCase
import com.thoren.manganimu.domain.anime.usecases.GetAnimeStreamUseCaseImpl
import com.thoren.manganimu.domain.anime.usecases.GetPopularAnimeUseCase
import com.thoren.manganimu.domain.anime.usecases.GetPopularAnimeUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal interface DomainAnimeModule {

    @Binds
    fun bindGetPopularAnimeUseCase(
        impl: GetPopularAnimeUseCaseImpl,
    ): GetPopularAnimeUseCase

    @Binds
    fun bindGetAnimeEpisodeUseCase(
        impl: GetAnimeEpisodeUseCaseImpl,
    ): GetAnimeEpisodeUseCase

    @Binds
    fun bindGetAnimeDetailsUseCase(
        impl: GetAnimeDetailsUseCaseImpl,
    ): GetAnimeDetailsUseCase

    @Binds
    fun bindGetAnimeStreamUseCase(
        impl: GetAnimeStreamUseCaseImpl,
    ): GetAnimeStreamUseCase

    @Binds
    fun bindGetAnimeRecommendationsUseCase(
        impl: GetAnimeRecommendationsUseCaseImpl,
    ): GetAnimeRecommendationsUseCase
}