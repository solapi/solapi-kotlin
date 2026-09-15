# SOLAPI Kotlin/Java SDK

[![Maven Central](https://img.shields.io/maven-central/v/com.solapi/sdk)](https://central.sonatype.com/artifact/com.solapi/sdk)
[![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](https://opensource.org/licenses/MIT)

Kotlin과 Java에서 SOLAPI 메시지 발송 서비스를 사용할 수 있는 공식 SDK입니다.

## AI와 함께 시작하기

### 사람이 읽을 가이드:
Claude Code, Cursor, Windsurf 등 AI 코딩 도구에 아래 프롬프트를 붙여넣기하세요:

```text
Implement messaging feature using SOLAPI SDK.
Guide: https://raw.githubusercontent.com/solapi/solapi-kotlin/main/LLM_GUIDE.md
```

### LLM 에이전트를 위한 가이드:
SDK 가이드를 가져온 후 LLM 가이드를 따르세요.

```bash
curl -s https://raw.githubusercontent.com/solapi/solapi-kotlin/main/LLM_GUIDE.md
```

| 상황 | 프롬프트 |
|------|----------|
| SMS/MMS 발송 | "SOLAPI SDK로 SMS 발송 코드 작성해줘" |
| 카카오 알림톡 | "SOLAPI SDK로 카카오 알림톡 발송 구현해줘" |
| 대량 발송 | "SOLAPI SDK로 대량 문자 발송 기능 추가해줘" |
| 예약 발송 | "SOLAPI SDK로 예약 발송 구현해줘" |
| 발송 조회 | "SOLAPI SDK로 발송 결과 조회 코드 작성해줘" |

## 설치

### Gradle (Kotlin DSL)

```kotlin
dependencies {
    implementation("com.solapi:sdk:1.2.0")
}
```

### Gradle (Groovy)

```groovy
dependencies {
    implementation 'com.solapi:sdk:1.2.0'
}
```

### Maven

```xml
<dependency>
    <groupId>com.solapi</groupId>
    <artifactId>sdk</artifactId>
    <version>1.2.0</version>
</dependency>
```

## 빠른 시작

### Java

```java
import com.solapi.sdk.SolapiClient;
import com.solapi.sdk.message.dto.response.MultipleDetailMessageSentResponse;
import com.solapi.sdk.message.model.Message;
import com.solapi.sdk.message.service.DefaultMessageService;

public class Main {
    public static void main(String[] args) {
        DefaultMessageService messageService = SolapiClient.INSTANCE.createInstance("API_KEY", "API_SECRET");

        Message message = new Message();
        message.setFrom("발신번호");
        message.setTo("수신번호");
        message.setText("안녕하세요. SOLAPI SDK 테스트입니다.");

        MultipleDetailMessageSentResponse response = messageService.send(message, null);
        System.out.println("Group ID: " + response.getGroupInfo().getGroupId());
    }
}
```

### Kotlin

```kotlin
import com.solapi.sdk.SolapiClient
import com.solapi.sdk.message.model.Message

fun main() {
    val messageService = SolapiClient.createInstance("API_KEY", "API_SECRET")

    val message = Message(
        from = "발신번호",
        to = "수신번호",
        text = "안녕하세요. SOLAPI SDK 테스트입니다."
    )

    val response = messageService.send(message)
    println("Group ID: ${response.groupInfo?.groupId}")
}
```

## 예제 실행

### 환경변수 설정

| 환경변수 | 설명 |       필수        |
|----------|------|:---------------:|
| `SOLAPI_API_KEY` | SOLAPI API 키 |        O        |
| `SOLAPI_API_SECRET` | SOLAPI API 시크릿 |        O        |
| `SOLAPI_SENDER` | 등록된 발신번호 |        O        |
| `SOLAPI_RECIPIENT` | 수신번호 |        O        |
| `SOLAPI_KAKAO_PF_ID` | 카카오 비즈니스 채널 ID | 카카오 계열 메시지 발송 시 |
| `SOLAPI_KAKAO_TEMPLATE_ID` | 카카오 알림톡 템플릿 ID |    알림톡 발송 시     |

### 실행 명령어

```bash
# Java 예제 실행
./gradlew :solapi-kotlin-example-java:run -Pexample=SendSms

# Kotlin 예제 실행
./gradlew :solapi-kotlin-example-kotlin:run -Pexample=SendSms
```

### JDK 8 사용자 안내

**SDK 사용 시**: 본 SDK는 JDK 8 이상에서 정상 동작합니다. Maven Central에서 의존성을 추가하면 JDK 8 환경에서 바로 사용 가능합니다.

**예제 실행 시**: 이 저장소의 예제를 직접 실행하려면 Gradle 9.x가 필요하며, 이는 **JDK 21 이상**을 요구합니다.

JDK 8만 설치된 환경에서 예제를 실행하려면:

#### 방법 1: 별도의 JDK 21+ 설치 (권장)

Gradle Toolchain이 자동으로 JDK 8을 다운로드하여 예제를 컴파일합니다. Gradle 실행용으로만 JDK 21 이상이 필요합니다.

```bash
# macOS (Homebrew)
brew install openjdk@21

# Ubuntu/Debian
sudo apt install openjdk-21-jdk

# SDKMAN (권장)
sdk install java 21.0.2-tem
```

#### 방법 2: 자신의 프로젝트에서 직접 테스트

JDK 8 환경의 자체 프로젝트에서 SDK를 추가하고 테스트할 수 있습니다:

```java
// build.gradle (Groovy)
plugins {
    id 'java'
}

java {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}

dependencies {
    implementation 'com.solapi:sdk:1.2.0'
}
```

```java
// src/main/java/MyTest.java
import com.solapi.sdk.SolapiClient;
import com.solapi.sdk.message.model.Message;
import com.solapi.sdk.message.service.DefaultMessageService;

public class MyTest {
    public static void main(String[] args) {
        DefaultMessageService messageService = SolapiClient.INSTANCE.createInstance(
            "YOUR_API_KEY",
            "YOUR_API_SECRET"
        );

        Message message = new Message();
        message.setFrom("발신번호");
        message.setTo("수신번호");
        message.setText("테스트 메시지");

        messageService.send(message, null);
    }
}
```

> **참고**: Gradle 7.x (JDK 11+) 또는 Gradle 6.x (JDK 8+)를 사용하는 프로젝트에서는 위 설정으로 바로 사용 가능합니다.

### 예제 목록

| 예제 | 설명 |
|------|------|
| `SendSms` | SMS 단건 발송 |
| `SendMms` | MMS 이미지 첨부 발송 |
| `SendBatch` | 대량 메시지 발송 |
| `SendScheduled` | 예약 발송 |
| `SendVoice` | 음성 메시지 발송 |
| `KakaoAlimtalk` | 카카오 알림톡 발송 |
| `KakaoBrandMessage` | 카카오 브랜드 메시지 발송 |
| `GetBalance` | 잔액 조회 |
| `GetMessageList` | 발송 내역 조회 |

## 지원 메시지 타입

| 타입 | 설명                              |
|------|---------------------------------|
| `SMS` | 단문문자 (80 byte 미만)               |
| `LMS` | 장문문자 (80 byte 이상, 2,000 byte 미만) |
| `MMS` | 이미지 포함 문자 (200KB 이내 이미지 1장)     |
| `ATA` | 카카오 알림톡                         |
| `BMS_*` | 카카오 브랜드 메시지 (템플릿, 자유형)          |
| `RCS_*` | RCS 문자 (SMS, LMS, MMS, TPL)     |
| `NSA` | 네이버 스마트 알림                      |
| `FAX` | 팩스                              |
| `VOICE` | 음성 메시지                          |

## 주요 기능

### MMS 이미지 첨부 발송

**Java:**
```java
// 이미지 업로드
String imageId = messageService.uploadFile(imageFile, StorageType.MMS, null);

// MMS 메시지 생성 및 발송
Message message = new Message();
message.setType(MessageType.MMS);
message.setFrom("발신번호");
message.setTo("수신번호");
message.setText("MMS 메시지 내용");
message.setSubject("MMS 제목");
message.setImageId(imageId);
messageService.send(message, null);
```

**이미지 규격:** JPG/JPEG, 최대 200KB, 권장 해상도 1000x1000 이하

### 대량 메시지 발송

**Java:**
```java
List<Message> messages = new ArrayList<>();
for (int i = 1; i <= 100; i++) {
    Message msg = new Message();
    msg.setFrom(sender);
    msg.setTo("010XXXX000" + i);
    msg.setText("메시지 " + i);
    messages.add(msg);
}

// 중복 수신번호 허용 옵션
SendRequestConfig config = new SendRequestConfig();
config.setAllowDuplicates(true);
messageService.send(messages, config);
```

- 한 번에 최대 **10,000건** 발송 가능
- `allowDuplicates = true`로 동일 수신번호 중복 발송 허용

### 예약 발송

**Java:**
```java
SendRequestConfig config = new SendRequestConfig();
config.setScheduledDateFromLocalDateTime(
    LocalDateTime.now().plusMinutes(10),
    ZoneId.of("Asia/Seoul")
);
messageService.send(message, config);
```

- 최소 **10분 후**부터 최대 **6개월 이내** 예약 가능
- 과거 시간 지정 시 즉시 발송 처리

### 카카오 알림톡

**Java:**
```java
Map<String, String> variables = new HashMap<>();
variables.put("name", "홍길동");
variables.put("code", "123456");

KakaoOption kakaoOption = new KakaoOption();
kakaoOption.setPfId("카카오채널ID");
kakaoOption.setTemplateId("템플릿ID");
kakaoOption.setVariables(variables);

Message message = new Message();
message.setType(MessageType.ATA);
message.setFrom("발신번호");
message.setTo("수신번호");
message.setKakaoOptions(kakaoOption);
messageService.send(message, null);
```

- 검수 승인된 템플릿만 사용 가능
- 정보성 메시지 전용 (광고 불가)

## API 레퍼런스

### 메시지 발송

| 메서드 | 설명 |
|--------|------|
| `send(message)` | 단건 메시지 발송 |
| `send(messages)` | 다건 메시지 발송 (최대 10,000건) |
| `send(message, config)` | 설정과 함께 발송 (예약, 중복 허용 등) |
| `uploadFile(file, type)` | 파일 업로드 (MMS, FAX 등) |

### 조회

| 메서드 | 설명 |
|--------|------|
| `getBalance()` | 잔액 조회 |
| `getQuota()` | 일일 발송량 한도 조회 |
| `getMessageList(request)` | 메시지 발송 내역 조회 |

### 카카오 템플릿 관리

| 메서드 | 설명 |
|--------|------|
| `getKakaoAlimtalkTemplates()` | 알림톡 템플릿 목록 조회 |
| `getKakaoAlimtalkTemplate(id)` | 알림톡 템플릿 상세 조회 |
| `createKakaoAlimtalkTemplate(request)` | 알림톡 템플릿 생성 |
| `getSendableKakaoAlimtalkTemplates()` | 발송 가능한 템플릿 조회 |
| `getKakaoBrandMessageTemplates()` | 브랜드 메시지 템플릿 조회 |

## 에러 처리

```java
try {
    messageService.send(message, null);
} catch (SolapiBadRequestException e) {
    System.out.println("잘못된 요청: " + e.getMessage());
} catch (SolapiInvalidApiKeyException e) {
    System.out.println("잘못된 API 키: " + e.getMessage());
} catch (SolapiMessageNotReceivedException e) {
    System.out.println("발송 실패: " + e.getMessage());
} catch (SolapiException e) {
    System.out.println("기타 오류: " + e.getMessage());
}
```

| 예외 클래스 | 설명 |
|-------------|------|
| `SolapiBadRequestException` | 잘못된 요청 파라미터 |
| `SolapiInvalidApiKeyException` | 유효하지 않은 API 키 |
| `SolapiApiKeyException` | API 키 관련 오류 |
| `SolapiFileUploadException` | 파일 업로드 실패 |
| `SolapiMessageNotReceivedException` | 메시지 수신 실패 |
| `SolapiEmptyResponseException` | 빈 응답 수신 |
| `SolapiUnknownException` | 알 수 없는 오류 |

## 요구 사항

- **Java 8** 이상
- **Kotlin 2.2.0** 이상 (Kotlin 사용 시)

## 관련 링크

- [SOLAPI 개발연동 문서](https://developers.solapi.com)
- [API 키 발급](https://console.solapi.com/credentials)
- [발신번호 등록](https://console.solapi.com/senderids)
- [API Reference (Dokka)](https://solapi.github.io/solapi-kotlin/)

## 라이선스

MIT License - 자세한 내용은 [LICENSE](LICENSE) 파일을 참조하세요.
