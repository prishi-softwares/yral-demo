package org.example.yarldemo.shared.core.utils

import kotlinx.serialization.KSerializer
import kotlinx.serialization.json.*

val json = Json {
    isLenient = true
    ignoreUnknownKeys = true
}

fun <T> toMap(serializer: KSerializer<T>, obj: T): Map<String, Any?> {
    return json.encodeToJsonElement(serializer, obj).jsonObject.toMap()
}

fun List<*>.toJsonElement(): JsonElement {
    val list: MutableList<JsonElement> = mutableListOf()
    this.forEach { value ->
        when (value) {
            null -> list.size //list.add(JsonNull) empty statement
            is Map<*, *> -> list.add(value.toJsonElement())
            is List<*> -> list.add(value.toJsonElement())
            is Boolean -> list.add(JsonPrimitive(value))
            is Number -> list.add(JsonPrimitive(value))
            is String -> list.add(JsonPrimitive(value))
            is Enum<*> -> list.add(JsonPrimitive(value.toString()))
            else -> throw IllegalStateException("Can't serialize unknown collection type: $value")
        }
    }
    return JsonArray(list)
}

fun Map<*, *>.toJsonElement(): JsonElement {
    val map: MutableMap<String, JsonElement> = mutableMapOf()
    this.forEach { (key, value) ->
        key as String
        when (value) {
            null -> map.remove(key) //map[key] = JsonNull
            is Map<*, *> -> map[key] = value.toJsonElement()
            is List<*> -> map[key] = value.toJsonElement()
            is Boolean -> map[key] = JsonPrimitive(value)
            is Number -> map[key] = JsonPrimitive(value)
            is String -> map[key] = JsonPrimitive(value)
            is Enum<*> -> map[key] = JsonPrimitive(value.toString())
            else -> throw IllegalStateException("Can't serialize unknown type: $value")
        }
    }
    return JsonObject(map)
}