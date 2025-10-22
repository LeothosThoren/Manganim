package com.thoren.manganimu.core.network.models.anime.recommendations


import kotlinx.serialization.InternalSerializationApi
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@InternalSerializationApi
@Serializable
data class DataResponse(
    @SerialName("mal_id")
    val malId: String,
    @SerialName("entry")
    val entry: List<EntryResponse>,
    @SerialName("content")
    val content: String,
    @SerialName("user")
    val user: UserResponse
)