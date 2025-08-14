# Nurigo Java SDK

### 실행방법

---

### 메시지 발송 

#### 자바 기준 
```java
DefaultMessageService solapiClient = SolapiClient.INSTANCE.createInstance("ENTER_YOUR_API_KEY", "ENTER_YOUR_API_SECRET_KEY");
Message message = new Message();

message.setFrom("발신번호");
message.setTo("수신번호");
message.setText("메시지 내용");

System.out.println(solapiClient.send(message));
```

#### 코틀린 기준
```kotlin
val solapiClient = SolapiClient.createInstance("ENTER_YOUR_API_KEY", "ENTER_YOUR_API_SECRET_KEY")

val message = Message(from = "발신번호", to = "수신번호", text = "메시지 내용")

println(solapiClient.send(message))
```

더 자세한 사용 방법 및 예제는 [SOLAPI SDK 예제 레포지터리](https://github.com/solapi/solapi-java-examples)를 참고 해주세요.
