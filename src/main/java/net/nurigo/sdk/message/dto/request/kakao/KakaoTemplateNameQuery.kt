package net.nurigo.sdk.message.dto.request.kakao

data class KakaoTemplateNameQuery(
    val name: String,
    val queryCondition: KakaoAlimtalkTemplateNameQueryCondition,
) {
    enum class KakaoAlimtalkTemplateNameQueryCondition {
        EQUALS,
        NOT_EQUALS,
        LIKE
    }
}
