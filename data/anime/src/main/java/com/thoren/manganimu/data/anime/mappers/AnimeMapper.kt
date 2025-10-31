package com.thoren.manganimu.data.anime.mappers

import com.thoren.manganimu.core.models.AnimeFailure
import com.thoren.manganimu.core.models.AnimeItem
import com.thoren.manganimu.core.network.models.anime.recommendations.DataResponse
import com.thoren.manganimu.core.network.models.anime.recommendations.EntryResponse
import com.thoren.manganimu.core.network.models.failure.ApiCallFailure
import com.thoren.manganimu.core.network.models.failure.HttpStatusCode
import kotlinx.serialization.InternalSerializationApi

@OptIn(InternalSerializationApi::class)
internal fun DataResponse.toAnimeItems(): List<AnimeItem> =
    entry.map { it.toAnimeItem() }

internal fun EntryResponse.toAnimeItem(): AnimeItem =
    AnimeItem(
        id = malId,
        title = title,
        coverImage = AnimeItem.CoverImage(
            medium = images.webp.smallImageUrl,
            large = images.webp.largeImageUrl,
            extraLarge = images.webp.largeImageUrl,
        )
    )

internal fun ApiCallFailure.toAnimeFailure() =
    when (this) {
        is ApiCallFailure.IO,
        is ApiCallFailure.Timeout,
            -> AnimeFailure.Network

        is ApiCallFailure.Parsing,
        is ApiCallFailure.Unknown,
            -> AnimeFailure.Technical

        is ApiCallFailure.Http,
            -> when (code) {
            HttpStatusCode.BAD_REQUEST -> AnimeFailure.BadRequest
            HttpStatusCode.NOT_FOUND -> AnimeFailure.NotFound
            HttpStatusCode.TOO_MANY_REQUEST_CODE -> AnimeFailure.TooManyRequests
            HttpStatusCode.SERVER_ERROR -> AnimeFailure.ServerError
            else -> AnimeFailure.Technical
        }
    }