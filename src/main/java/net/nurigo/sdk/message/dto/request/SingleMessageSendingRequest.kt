package net.nurigo.sdk.message.dto.request

import kotlinx.serialization.Serializable
import net.nurigo.sdk.message.model.Message

@Serializable
data class SingleMessageSendingRequest(
    val message: Message,
): AbstractDefaultMessageRequest()
