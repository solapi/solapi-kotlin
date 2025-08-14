package com.solapi.sdk.message.dto.response.kakao

import kotlinx.serialization.Serializable
import com.solapi.sdk.message.dto.response.common.CommonListResponse

@Serializable
data class KakaoAlimtalkTemplateListResponse(
    var templateList: List<KakaoAlimtalkTemplateResponse>? = null,
) : CommonListResponse()
