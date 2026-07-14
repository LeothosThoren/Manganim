package com.thoren.manganimu.domain.anime.di

import com.thoren.manganimu.domain.anime.usecases.GetAnimeRecommendationsUseCase
import com.thoren.manganimu.domain.anime.usecases.GetAnimeRecommendationsUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal interface DomainAnimeModule {

    @Binds
    fun bindGetAnimeRecommendationsUseCase(
        impl: GetAnimeRecommendationsUseCaseImpl,
    ): GetAnimeRecommendationsUseCase
}
