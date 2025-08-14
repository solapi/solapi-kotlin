package net.nurigo.sdk.message.dto.request

import kotlinx.serialization.Contextual
import java.time.LocalDateTime
import kotlinx.serialization.Serializable
import net.nurigo.sdk.message.model.CommonMessageProperty

@Serializable
data class MessageListBaseRequest(
    override var to: String? = null,
    override var from: String? = null,
    var startKey: String? = null,
    var limit: Int? = null,
    var messageId: String? = null,
    var groupId: String? = null,
    var type: String? = null,
    var statusCode: String? = null,
    var criteria: String? = null,
    var cond: String? = null,
    var value: String? = null,

    @Contextual
    var startDate: LocalDateTime? = null,

    @Contextual
    var endDate: LocalDateTime? = null
) : CommonMessageProperty
