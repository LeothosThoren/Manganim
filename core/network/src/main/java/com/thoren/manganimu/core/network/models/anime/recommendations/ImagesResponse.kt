package com.thoren.manganimu.core.network.models.anime.recommendations


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ImagesResponse(
    @SerialName("jpg")
    val jpg: JpgResponse,
    @SerialName("webp")
    val webp: WebpResponse
)