package com.example.kdp.chapter02

class MailBuilder {
    private var to: List<String> = listOf()
    private var cc: List<String> = listOf()
    private var title: String = ""
    private var message: String = ""
    private var important: Boolean = false

    class Mail internal constructor(
        val to: List<String>,
        val cc: List<String>?,
        val title: String?,
        val message: String?,
        val important: Boolean
    )

    fun build(): Mail {
        if (to.isEmpty()) throw RuntimeException("To propert is empty")
        return Mail(to, cc, title, message, important)
    }

    fun message(message: String): MailBuilder {
        this.message = message
        return this
    }
}

/*
Cons
- make all of the optional argument mutable
- also nullable check
- syntax is very verbose
* */
data class Mail_V2(
    val to: List<String>,
    private var _message: String? = null,
    private var _cc: List<String>? = null,
    private var _title: String? = null,
    private var _important: Boolean? = null
) {
    fun message(message: String) = apply { _message = message }
}

data class Mail_V3(
    val to: List<String>,
    val cc: List<String> = listOf(),
    val title: String = "",
    val message: String = "",
    val important: Boolean = false
)

fun main() {
    val email = MailBuilder().message("hello@hello.com").build()

    val kotlinBuilder = Mail_V2(listOf("manager@company.com")).message("Ping")

    val mail = Mail_V3(listOf("manager@company.com"), listOf(), "Ping")

    val mail2 = Mail_V3(title = "Hello", message = "There", to = listOf("my@dear.cat"))
}