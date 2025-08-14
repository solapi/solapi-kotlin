package net.nurigo.sdk.message.model.fax

import kotlinx.serialization.Serializable

@Serializable
data class FaxOption(
    var fileIds: List<String>? = null,
)