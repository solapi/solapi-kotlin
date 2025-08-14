package net.nurigo.sdk.message.dto.request

import kotlinx.serialization.Serializable
import net.nurigo.sdk.message.model.StorageType

@Serializable
data class FileUploadRequest(
    var file: String? = null,
    var type: StorageType? = null,
    var name: String? = null,
    var link: String? = null,
)
