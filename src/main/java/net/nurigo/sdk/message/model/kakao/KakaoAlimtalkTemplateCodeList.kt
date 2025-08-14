package net.nurigo.sdk.message.model.kakao

import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable
import java.time.Instant

@Serializable
data class KakaoAlimtalkTemplateCodeList(
    var code: String? = null,
    var service: String? = null,
    var isMain: Boolean? = null,
    var status: KakaoAlimtalkTemplateStatus? = null,
    var dormant: Boolean? = null,
    var outDated: Boolean? = null,
    var comments: List<Comment>? = null
) {
    @Serializable
    data class Comment(
        var memberId: String? = null,
        var isAdmin: Boolean? = null,
        var content: String? = null,
        @Contextual
        var dateCreated: Instant? = null
    )
}
