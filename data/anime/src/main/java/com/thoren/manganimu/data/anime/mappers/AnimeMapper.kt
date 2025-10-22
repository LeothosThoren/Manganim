package com.thoren.manganimu.data.anime.mappers

import com.thoren.manganimu.core.models.AnimeDetail
import com.thoren.manganimu.core.models.AnimeFailure
import com.thoren.manganimu.core.models.AnimeItem
import com.thoren.manganimu.core.models.AnimeStream
import com.thoren.manganimu.core.models.EpisodeItem
import com.thoren.manganimu.core.network.models.anime.AnimeDetailResponse
import com.thoren.manganimu.core.network.models.anime.AnimeEpisodeResponse
import com.thoren.manganimu.core.network.models.anime.EpisodeResponse
import com.thoren.manganimu.core.network.models.anime.PopularAnimeResponse
import com.thoren.manganimu.core.network.models.anime.recommendations.DataResponse
import com.thoren.manganimu.core.network.models.anime.recommendations.EntryResponse
import com.thoren.manganimu.core.network.models.anime.stream.StreamResponse
import com.thoren.manganimu.core.network.models.anime.stream.VideoStreamResponse
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

internal fun PopularAnimeResponse.toAnimeItems(): List<AnimeItem> = results.map { it.toAnimeItem() }

internal fun PopularAnimeResponse.ResultResponse.toAnimeItem(): AnimeItem =
    AnimeItem(
        id = id,
        title = title.english,
        coverImage = AnimeItem.CoverImage(
            medium = coverImage.medium,
            large = coverImage.large,
            extraLarge = coverImage.extraLarge,
        )
    )

internal fun AnimeEpisodeResponse.toEpisodeItems(): List<EpisodeItem> =
    episodes.map { it.toEpisodeItem() }

internal fun EpisodeResponse.toEpisodeItem(): EpisodeItem =
    EpisodeItem(
        id = id,
        title = title,
        description = description,
        number = number,
        image = image
    )

internal fun AnimeDetailResponse.toAnimeDetail(): AnimeDetail =
    AnimeDetail(
        bannerImage = bannerImage,
        coverImage = AnimeItem.CoverImage(
            medium = coverImage.medium,
            large = coverImage.large,
            extraLarge = coverImage.extraLarge,
        ),
        description = description,
        dub = dub,
        episodes = episodes,
        format = format,
        genres = genres,
        id = id,
        idMal = idMal,
        idProvider = idProvider.idGogo,
        season = season,
        status = status,
        title = title.english,
        year = year
    )

internal fun VideoStreamResponse.toAnimeStream(): AnimeStream =
    AnimeStream(
        id = info.id,
        title = info.title,
        episode = info.episode,
        main = stream.multi.main.toStreamDetail(),
        backup = stream.multi.backup.toStreamDetail()
    )

internal fun StreamResponse.MultiResponse.StreamDetailResponse.toStreamDetail(): AnimeStream.StreamDetail =
    AnimeStream.StreamDetail(
        url = url,
        isM3U8 = isM3U8,
        quality = quality
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