@file:OptIn(InternalSerializationApi::class)

package com.thoren.manganimu.core.network.models.anime.recommendations


import kotlinx.serialization.InternalSerializationApi
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AnimeRecommendationsResponse(
    @SerialName("data")
    val data: List<DataResponse>,
    @SerialName("pagination")
    val pagination: PaginationResponse
)