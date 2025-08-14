package net.nurigo.sdk.message.service

import net.nurigo.sdk.message.model.Balance
import net.nurigo.sdk.message.model.Quota
import net.nurigo.sdk.message.dto.request.FileUploadRequest
import net.nurigo.sdk.message.dto.request.MultipleDetailMessageSendingRequest
import net.nurigo.sdk.message.dto.request.kakao.KakaoAlimtalkTemplateMutationRequest
import net.nurigo.sdk.message.dto.request.kakao.KakaoAlimtalkTemplateUpdateNameRequest
import net.nurigo.sdk.message.dto.response.FileUploadResponse
import net.nurigo.sdk.message.dto.response.MessageListResponse
import net.nurigo.sdk.message.dto.response.MultipleDetailMessageSentResponse
import net.nurigo.sdk.message.dto.response.kakao.KakaoAlimtalkTemplateListResponse
import net.nurigo.sdk.message.dto.response.kakao.KakaoAlimtalkTemplateResponse
import net.nurigo.sdk.message.dto.response.kakao.KakaoBrandMessageTemplateListResponse
import net.nurigo.sdk.message.model.kakao.KakaoAlimtalkTemplateCategory
import retrofit2.Call
import retrofit2.http.*

interface MessageHttpService : MessageService {

    @JvmSuppressWildcards
    @GET("/messages/v4/list")
    fun getMessageList(@QueryMap parameter: Map<String, Any?>? = null): Call<MessageListResponse>

    @POST("/messages/v4/send-many/detail")
    fun sendManyDetail(@Body parameter: MultipleDetailMessageSendingRequest): Call<MultipleDetailMessageSentResponse>

    @POST("/storage/v1/files")
    fun uploadFile(@Body fileUploadRequest: FileUploadRequest): Call<FileUploadResponse>

    @GET("/cash/v1/balance")
    fun getBalance(): Call<Balance>

    @GET("/quota/v1/me")
    fun getQuota(): Call<Quota>

    @GET("/kakao/v2/templates/categories")
    fun getKakaoAlimtalkTemplateCategories(): Call<List<KakaoAlimtalkTemplateCategory>>

    @POST("/kakao/v2/templates")
    fun createKakaoAlimtalkTemplate(@Body parameter: KakaoAlimtalkTemplateMutationRequest): Call<KakaoAlimtalkTemplateResponse>

    @PUT("/kakao/v2/templates/{templateId}")
    fun updateKakaoAlimtalkTemplate(@Path("templateId") templateId: String, @Body parameter: KakaoAlimtalkTemplateMutationRequest): Call<KakaoAlimtalkTemplateResponse>

    @PUT("/kakao/v2/templates/{templateId}/inspection")
    fun requestKakaoAlimtalkTemplateInspection(@Path("templateId") templateId: String): Call<KakaoAlimtalkTemplateResponse>

    @GET("/kakao/v2/templates/{templateId}")
    fun getKakaoAlimtalkTemplate(@Path("templateId") templateId: String): Call<KakaoAlimtalkTemplateResponse>

    @GET("/kakao/v2/templates")
    fun getKakaoAlimtalkTemplates(@QueryMap(encoded = true) parameter: Map<String, String> = emptyMap()): Call<KakaoAlimtalkTemplateListResponse>

    @GET("/kakao/v2/templates/sendable")
    fun getSendableKakaoAlimtalkTemplates(@QueryMap parameter: Map<String, String> = emptyMap()): Call<List<KakaoAlimtalkTemplateResponse>>

    @PUT("/kakao/v2/templates/{templateId}/inspection/cancel")
    fun cancelKakaoAlimtalkTemplateInspection(@Path("templateId") templateId: String): Call<KakaoAlimtalkTemplateResponse>

    @DELETE("/kakao/v2/templates/{templateId}")
    fun removeKakaoAlimtalkTemplate(@Path("templateId") templateId: String): Call<KakaoAlimtalkTemplateResponse>

    @POST("/kakao/v2/templates/{templateId}/relese-dormant")
    fun requestKakaoAlimtalkTemplateReactivation(@Path("templateId") templateId: String): Call<KakaoAlimtalkTemplateResponse>

    @PUT("/kakao/v2/templates/{templateId}/name")
    fun updateKakaoAlimtalkTemplateName(@Path("templateId") templateId: String, @Body name: KakaoAlimtalkTemplateUpdateNameRequest): Call<KakaoAlimtalkTemplateResponse>

    @GET("/kakao/v2/brand-templates")
    fun getKakaoBrandMessageTemplates(@QueryMap parameter: Map<String, String> = emptyMap()): Call<KakaoBrandMessageTemplateListResponse>
}