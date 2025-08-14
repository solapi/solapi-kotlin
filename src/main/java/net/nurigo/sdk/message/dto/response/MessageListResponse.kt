package net.nurigo.sdk.message.dto.response

import kotlinx.serialization.Serializable
import net.nurigo.sdk.message.dto.response.common.CommonListResponse
import net.nurigo.sdk.message.model.Message

@Serializable
data class MessageListResponse(
    var messageList: Map<String, Message>? = null
) : CommonListResponse()
