package com.solapi.sdk.message.dto.response

import kotlinx.serialization.Serializable
import com.solapi.sdk.message.dto.response.common.CommonListResponse
import com.solapi.sdk.message.model.Message

@Serializable
data class MessageListResponse(
    var messageList: Map<String, Message>? = null
) : CommonListResponse()
