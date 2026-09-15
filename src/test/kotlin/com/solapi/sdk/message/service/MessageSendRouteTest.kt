package com.solapi.sdk.message.service

import com.solapi.sdk.message.dto.request.SendRequestConfig
import com.solapi.sdk.message.model.Message
import kotlin.test.AfterTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue
import mockwebserver3.MockResponse
import mockwebserver3.MockWebServer

class MessageSendRouteTest {

    private val server = MockWebServer().apply { start() }

    private val messageService: DefaultMessageService
        get() = DefaultMessageService("test-api-key", "test-api-secret", server.url("/").toString())

    @AfterTest
    fun tearDown() {
        server.close()
    }

    private fun enqueueFastSendResponse() {
        val body = javaClass.classLoader.getResourceAsStream("fast_send_response.json")!!
            .bufferedReader()
            .readText()
        server.enqueue(
            MockResponse.Builder()
                .code(200)
                .setHeader("Content-Type", "application/json")
                .body(body)
                .build()
        )
    }

    @Test
    fun `발송 요청은 고속접수 엔드포인트로 전송된다`() {
        // Given
        enqueueFastSendResponse()
        val message = Message(to = "01000000000", from = "01000000000", text = "테스트")

        // When
        messageService.send(message)

        // Then
        val request = server.takeRequest()
        assertEquals("POST", request.method)
        assertEquals("/messages/v4/send-many/fast", request.url.encodedPath)
    }

    @Test
    fun `고속접수 응답의 메시지 목록과 그룹 정보를 읽을 수 있다`() {
        // Given
        enqueueFastSendResponse()
        val message = Message(to = "01000000000", from = "01000000000", text = "테스트")

        // When
        val response = messageService.send(message, SendRequestConfig(showMessageList = true))

        // Then
        assertEquals(2, response.messageList.size)
        assertEquals("2000", response.messageList.first().statusCode)
        assertTrue(response.failedMessageList.isEmpty())
        assertEquals("v4-fast", response.groupInfo?.apiVersion)
        assertEquals(2, response.groupInfo?.count?.registeredSuccess)
    }

    @Test
    fun `고속접수 응답에 없는 필드는 기존 기본값으로 읽힌다`() {
        // Given
        enqueueFastSendResponse()
        val message = Message(to = "01000000000", from = "01000000000", text = "테스트")

        // When
        val groupInfo = messageService.send(message).groupInfo

        // Then
        assertEquals(false, groupInfo?.isRefunded)
        assertEquals(emptyMap(), groupInfo?.countForCharge?.cta)
        assertEquals(0f, groupInfo?.app?.profit?.cti)
    }
}
