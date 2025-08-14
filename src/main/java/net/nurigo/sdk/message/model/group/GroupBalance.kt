package net.nurigo.sdk.message.model.group

import kotlinx.serialization.Serializable

@Serializable
data class GroupBalance(
    val requested: Float? = null,
    val replacement: Float? = null,
    val additional: Float? = null,
    val refund: Float? = null,
    val sum: Float? = null
)
