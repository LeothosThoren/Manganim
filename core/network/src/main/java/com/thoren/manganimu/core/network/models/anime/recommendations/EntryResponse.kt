package com.thoren.manganimu.core.network.models.anime.recommendations


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class EntryResponse(
    @SerialName("mal_id")
    val malId: Int,
    @SerialName("url")
    val url: String,
    @SerialName("images")
    val images: ImagesResponse,
    @SerialName("title")
    val title: String
)