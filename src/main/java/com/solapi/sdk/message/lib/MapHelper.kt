package com.solapi.sdk.message.lib

import kotlinx.serialization.json.*

class MapHelper {

    companion object {
        inline fun <reified T> toMap(obj: T): Map<String, Any?> {
            return jsonObjectToMap(JsonSupport.json.encodeToJsonElement(obj).jsonObject)
        }

        fun jsonObjectToMap(element: JsonObject): Map<String, Any?> {
            return element.entries.associate {
                it.key to extractValue(it.value)
            }
        }

        private fun extractValue(element: JsonElement): Any? {
            return when (element) {
                is JsonNull -> null
                is JsonPrimitive -> element.content
                is JsonArray -> element.map { extractValue(it) }
                is JsonObject -> jsonObjectToMap(element)
            }
        }
    }
}