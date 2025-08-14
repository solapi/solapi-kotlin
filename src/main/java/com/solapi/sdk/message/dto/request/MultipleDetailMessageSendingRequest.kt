package com.solapi.sdk.message.dto.request

import kotlinx.serialization.Contextual
import java.time.Instant
import kotlinx.serialization.Serializable
import com.solapi.sdk.message.model.Message

@Serializable
data class MultipleDetailMessageSendingRequest(
    var messages: List<Message> = emptyList(),
    @Contextual
    var scheduledDate: Instant? = null,
    var showMessageList: Boolean = false,
) : AbstractDefaultMessageRequest()
