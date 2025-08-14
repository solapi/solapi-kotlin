package com.solapi.sdk.message.dto.request

import kotlinx.serialization.Serializable
import com.solapi.sdk.message.model.Message

@Serializable
data class SingleMessageSendingRequest(
    val message: Message,
): AbstractDefaultMessageRequest()
