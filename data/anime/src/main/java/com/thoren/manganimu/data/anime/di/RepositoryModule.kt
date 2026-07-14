package com.thoren.manganimu.data.anime.di

import com.thoren.manganimu.data.anime.AnimeRepositoryImpl
import com.thoren.manganimu.domain.anime.repositories.AnimeRecommendationsRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal interface RepositoryModule {

    @Binds
    fun bindAnimeRecommendationsRepository(
        impl: AnimeRepositoryImpl,
    ): AnimeRecommendationsRepository
}
