package net.nurigo.sdk.message.dto.request

import kotlinx.serialization.Serializable
import net.nurigo.sdk.Version

@Serializable
data class DefaultAgent(
    val sdkVersion: String = "java/${Version.SDK_VERSION}",
    val osPlatform: String = "${System.getProperty("os.name")} | ${System.getProperty("java.version")} | ${System.getProperty("os.arch")}"
)
