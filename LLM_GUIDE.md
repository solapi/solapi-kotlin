# SOLAPI Kotlin/Java SDK - LLM Guide

Technical guide for LLM agents writing code using the SDK.

## Agent Workflow

LLM agents should use the AskUserQuestion tool to ask users about their messaging needs before writing code.

### Required Questions

Ask the user the following questions using AskUserQuestion:

1. **Message Type** - Which type of message do you want to send?
   - SMS/LMS (Text message)
   - MMS (Image message)
   - Kakao Alimtalk (Notification)
   - Kakao Brand Message (Freeform/Template)
   - RCS
   - Voice
   - FAX

2. **Programming Language** - Which language are you using?
   - Kotlin
   - Java

3. **Send Mode** - How do you want to send messages?
   - Single message
   - Batch send (multiple recipients)
   - Scheduled send

After gathering requirements, proceed to the relevant sections in this guide.

## Quick Reference

| Item | Value |
|------|-------|
| SDK | `com.solapi:sdk:1.2.0` |
| Docs | https://developers.solapi.com/llms.txt |
| API Ref | https://solapi.github.io/solapi-kotlin/ |
| Java | 8+ |
| Kotlin | 1.8+ |

## JDK 8 Note

JDK 8 does not support `Map.of()`, `List.of()`. Use HashMap, ArrayList instead:
```java
Map<String, String> variables = new HashMap<>();
variables.put("name", "홍길동");
```

## Setup

**Gradle (Kotlin DSL):**
```kotlin
implementation("com.solapi:sdk:1.2.0")
```

**Maven:**
```xml
<dependency>
    <groupId>com.solapi</groupId>
    <artifactId>sdk</artifactId>
    <version>1.2.0</version>
</dependency>
```

## Core Pattern

**Kotlin:**
```kotlin
val service = SolapiClient.createInstance("API_KEY", "API_SECRET")
val message = Message(from = "발신번호", to = "수신번호", text = "내용")
val response = service.send(message)
```

**Java:**
```java
DefaultMessageService service = SolapiClient.INSTANCE.createInstance("API_KEY", "API_SECRET");
Message message = new Message();
message.setFrom("발신번호");
message.setTo("수신번호");
message.setText("내용");
service.send(message, null);
```

## Message Types

| Type | Field | Notes |
|------|-------|-------|
| SMS | text | < 80 bytes |
| LMS | text | 80-2000 bytes |
| MMS | imageId | Use after uploadFile(), JPG/JPEG, max 200KB |
| ATA | kakaoOptions | Kakao Alimtalk, template required |
| BMS_* | kakaoOptions | Kakao Brand Message |
| RCS_* | rcsOptions | RCS message |
| NSA | naverOptions | Naver Smart Notification |
| FAX | fileId | Use after uploadFile() |
| VOICE | voiceOptions | Voice message |

## KakaoOption

```kotlin
KakaoOption(
    pfId = "카카오채널ID",
    templateId = "템플릿ID",
    variables = mapOf("name" to "홍길동", "code" to "123456")
)
```

```java
KakaoOption kakaoOption = new KakaoOption();
kakaoOption.setPfId("카카오채널ID");
kakaoOption.setTemplateId("템플릿ID");
Map<String, String> variables = new HashMap<>();
variables.put("name", "홍길동");
variables.put("code", "123456");
kakaoOption.setVariables(variables);
```

## File Upload (MMS)

**Kotlin:**
```kotlin
val imageId = service.uploadFile(file, StorageType.MMS)
val message = Message(
    type = MessageType.MMS,
    from = "발신번호",
    to = "수신번호",
    text = "MMS 내용",
    subject = "제목",
    imageId = imageId
)
service.send(message)
```

**Java:**
```java
String imageId = service.uploadFile(file, StorageType.MMS, null);
Message message = new Message();
message.setType(MessageType.MMS);
message.setFrom("발신번호");
message.setTo("수신번호");
message.setText("MMS 내용");
message.setSubject("제목");
message.setImageId(imageId);
service.send(message, null);
```

## Batch Send

**Kotlin:**
```kotlin
val messages = recipients.map { recipient ->
    Message(from = sender, to = recipient, text = "메시지")
}
service.send(messages)  // max 10,000

// 중복 번호 허용
val config = SendRequestConfig().apply { allowDuplicates = true }
service.send(messages, config)
```

**Java:**
```java
List<Message> messages = new ArrayList<>();
for (String recipient : recipients) {
    Message msg = new Message();
    msg.setFrom(sender);
    msg.setTo(recipient);
    msg.setText("메시지");
    messages.add(msg);
}
service.send(messages, null);  // max 10,000

// 중복 번호 허용
SendRequestConfig config = new SendRequestConfig();
config.setAllowDuplicates(true);
service.send(messages, config);
```

## Scheduled Send

**Kotlin:**
```kotlin
val config = SendRequestConfig().apply {
    setScheduledDateFromLocalDateTime(
        LocalDateTime.now().plusMinutes(10),
        ZoneId.of("Asia/Seoul")
    )
}
service.send(message, config)
```

**Java:**
```java
SendRequestConfig config = new SendRequestConfig();
config.setScheduledDateFromLocalDateTime(
    LocalDateTime.now().plusMinutes(10),
    ZoneId.of("Asia/Seoul")
);
service.send(message, config);
```

Constraints: Minimum 10 minutes, maximum 6 months in advance

## API Methods

**Send:**
| Method | Description |
|--------|-------------|
| `send(message)` | Send single message |
| `send(messages)` | Send multiple messages (max 10,000) |
| `send(message, config)` | Send with configuration |
| `uploadFile(file, type)` | Upload file |

**Query:**
| Method | Description |
|--------|-------------|
| `getBalance()` | Get balance |
| `getQuota()` | Get daily quota |
| `getMessageList(request)` | Get message history |

**Kakao Templates:**
| Method | Description |
|--------|-------------|
| `getKakaoAlimtalkTemplates()` | Get Alimtalk template list |
| `getKakaoAlimtalkTemplate(id)` | Get template details |
| `getSendableKakaoAlimtalkTemplates()` | Get sendable templates |

## Exceptions

| Exception | Cause |
|-----------|-------|
| `SolapiBadRequestException` | Invalid parameters |
| `SolapiInvalidApiKeyException` | API key error |
| `SolapiApiKeyException` | API key related error |
| `SolapiFileUploadException` | File upload failed |
| `SolapiMessageNotReceivedException` | Send failed |
| `SolapiEmptyResponseException` | Empty response |
| `SolapiUnknownException` | Unknown error |

## Error Handling Pattern

**Kotlin:**
```kotlin
try {
    service.send(message)
} catch (e: SolapiBadRequestException) {
    // 잘못된 요청
} catch (e: SolapiInvalidApiKeyException) {
    // API 키 오류
} catch (e: SolapiMessageNotReceivedException) {
    // 발송 실패
} catch (e: SolapiException) {
    // 기타 오류
}
```

**Java:**
```java
try {
    service.send(message, null);
} catch (SolapiBadRequestException e) {
    // 잘못된 요청
} catch (SolapiInvalidApiKeyException e) {
    // API 키 오류
} catch (SolapiMessageNotReceivedException e) {
    // 발송 실패
} catch (SolapiException e) {
    // 기타 오류
}
```

## Environment Variables

| Variable | Description |
|----------|-------------|
| `SOLAPI_API_KEY` | API key |
| `SOLAPI_API_SECRET` | API secret |
| `SOLAPI_SENDER` | Sender number |
| `SOLAPI_KAKAO_PF_ID` | Kakao channel ID |
| `SOLAPI_KAKAO_TEMPLATE_ID` | Alimtalk template ID |

## Imports

**Kotlin:**
```kotlin
import com.solapi.sdk.SolapiClient
import com.solapi.sdk.message.model.Message
import com.solapi.sdk.message.model.MessageType
import com.solapi.sdk.message.model.StorageType
import com.solapi.sdk.message.model.SendRequestConfig
import com.solapi.sdk.message.model.kakao.KakaoOption
```

**Java:**
```java
import com.solapi.sdk.SolapiClient;
import com.solapi.sdk.message.model.Message;
import com.solapi.sdk.message.model.MessageType;
import com.solapi.sdk.message.model.StorageType;
import com.solapi.sdk.message.model.SendRequestConfig;
import com.solapi.sdk.message.model.kakao.KakaoOption;
import com.solapi.sdk.message.service.DefaultMessageService;
```
