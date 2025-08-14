package com.solapi.sdk.message.dto.response.kakao

import kotlinx.serialization.Serializable
import com.solapi.sdk.message.dto.response.common.CommonListResponse
import com.solapi.sdk.message.model.kakao.KakaoBrandMessageTemplate

@Serializable
data class KakaoBrandMessageTemplateListResponse(
    var templateList: List<KakaoBrandMessageTemplate>? = null,
) : CommonListResponse()
