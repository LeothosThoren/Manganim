package com.thoren.manganimu.core.network.models.anime.recommendations


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UserResponse(
    @SerialName("url")
    val url: String,
    @SerialName("username")
    val username: String
)