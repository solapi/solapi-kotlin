package net.nurigo.sdk.message.dto.response.kakao

import kotlinx.serialization.Serializable
import net.nurigo.sdk.message.dto.response.common.CommonListResponse
import net.nurigo.sdk.message.model.kakao.KakaoBrandMessageTemplate

@Serializable
data class KakaoBrandMessageTemplateListResponse(
    var templateList: List<KakaoBrandMessageTemplate>? = null,
) : CommonListResponse()
