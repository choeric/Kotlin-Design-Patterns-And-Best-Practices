package com.example.kdp.chapter02

interface Property {
    val name: String
    val value: Any
}

interface ServerConfiguration {
    val properties: List<Property>
}

// Casts
data class PropertyImpl(
    override val name: String,
    override val value: Any
) : Property

// Subclassing
data class IntProperty(
    override val name: String,
    override val value: Int
) : Property

data class StringProperty(
    override val name: String,
    override val value: Any
) : Property

data class ServerConfigurationImpl(
    override val properties: List<Property>
) : ServerConfiguration

fun server(propertyStrings: List<String>) : ServerConfiguration {
    val parsedProperties = mutableListOf<Property>()
    for (p in propertyStrings) {
        parsedProperties += Parser.property(p)
    }
    return ServerConfigurationImpl(parsedProperties)
}

// with Static Factory Method
class Parser {
    companion object {
        fun propertyByCast(prop: String): Property {
            val (name, value) = prop.split(":")
            return when(name) {
                "port" -> PropertyImpl(name, value.trim().toInt())
                "environment" -> PropertyImpl(name, value.trim())
                else -> throw RuntimeException("Unknown property: $name")
            }
        }

        fun property(prop: String): Property {
            val (name, value) = prop.split(":")
            return when(name) {
                "port" -> IntProperty(name, value.trim().toInt())
                "environment" -> StringProperty(name, value.trim())
                else -> throw RuntimeException("Unknown property: $name")
            }
        }
    }
}

fun main() {
    val expectPort = 8080
    val expectEnvironment = "production"

    // Casts
    val castPortProperty: Int = Parser.propertyByCast("port: $expectPort").value as Int
    val castEnvironment: String = Parser.propertyByCast("environment: $expectEnvironment").value as String
    assert(castPortProperty == expectPort)
    assert(castEnvironment == expectEnvironment)

    // Subclassing
    val portProperty = Parser.property("port: $expectPort").value
    val environment = Parser.property("environment: $expectEnvironment").value
    assert(portProperty == expectPort)
    assert(environment == expectEnvironment)

    println(server(listOf("port: $expectPort", "environment: $expectEnvironment")))
}
