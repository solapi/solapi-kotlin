package com.solapi.sdk.message.dto.response

import kotlinx.serialization.Serializable
import com.solapi.sdk.message.model.FailedMessage
import com.solapi.sdk.message.model.group.GroupInfo

@Serializable
data class MultipleDetailMessageSentResponse(
    var failedMessageList: List<FailedMessage> = emptyList(),
    var groupInfo: GroupInfo? = null,
    var messageList: List<MessageList> = emptyList(),
) {
    @Serializable
    data class MessageList(
        var messageId: String? = null,
        var statusCode: String? = null,
        var customFields: Map<String, String>? = null,
        var statusMessage: String? = null
    )
}
