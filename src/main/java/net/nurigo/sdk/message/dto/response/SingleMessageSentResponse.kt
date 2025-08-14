package net.nurigo.sdk.message.dto.response

import kotlinx.serialization.Serializable
import net.nurigo.sdk.message.model.MessageType

@Serializable
data class SingleMessageSentResponse(
    val groupId: String,
    val to: String,
    val from: String,
    val type: MessageType,
    val statusMessage: String,
    val country: String,
    val messageId: String,
    val statusCode: String,
    val accountId: String
)
