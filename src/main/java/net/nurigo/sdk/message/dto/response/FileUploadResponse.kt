package net.nurigo.sdk.message.dto.response

import kotlinx.serialization.Serializable

@Serializable
data class FileUploadResponse(
    var fileId: String? = null
)
