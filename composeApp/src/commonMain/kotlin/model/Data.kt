package model

import kotlinx.serialization.Serializable

@Serializable
data class Data(
    val id: Int,
    val avatar: String,
    val email: String,
    val first_name: String,
    val last_name: String
)