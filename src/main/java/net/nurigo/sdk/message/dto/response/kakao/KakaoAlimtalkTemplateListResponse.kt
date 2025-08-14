package net.nurigo.sdk.message.dto.response.kakao

import kotlinx.serialization.Serializable
import net.nurigo.sdk.message.dto.response.common.CommonListResponse

@Serializable
data class KakaoAlimtalkTemplateListResponse(
    var templateList: List<KakaoAlimtalkTemplateResponse>? = null,
) : CommonListResponse()
