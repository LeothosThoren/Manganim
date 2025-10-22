package com.thoren.manganimu.core.network.di

import com.thoren.manganimu.core.network.RetrofitAnimeNetworkImpl
import com.thoren.manganimu.core.network.networkdatasources.AnimeNetworkDataSource
import com.thoren.manganimu.core.network.services.AnimeApiService
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
internal interface AnimeNetworkModule {

    @Binds
    fun bindAnimeNetworkDataSource(
        retrofitAnimeNetwork: RetrofitAnimeNetworkImpl
    ): AnimeNetworkDataSource

    companion object {
        @Provides
        @BaseUrl
        fun baseUrl(): String = "https://api.jikan.moe/"

        @Provides
        fun animeApiService(retrofit: Retrofit): AnimeApiService {
            return retrofit.create(AnimeApiService::class.java)
        }
    }
}
