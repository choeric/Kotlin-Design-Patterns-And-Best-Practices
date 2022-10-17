package com.example.kdp.chapter02

class Server private constructor(port: Long) {
    init {
        println("Server started on port $port")
    }

    companion object {
        fun withPort(port: Long) = Server(port)
    }
}

fun main() {
//    Server(100)
//    val server1 = Server(8080)
    val server2 = Server.withPort(8080)
}