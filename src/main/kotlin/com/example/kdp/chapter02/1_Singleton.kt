package com.example.kdp.chapter02

object NoMovieList

val myMovie = NoMovieList
val yourMovie = NoMovieList

fun printMovies(movies: List<String>) {
    for (m in movies)
        println(m)
}

object Logger {
    init {
        println("I was accessed for the first time")
        // Initialization logic goes here
    }
    fun log() = println("Logging")
}

fun main() {
    println(myMovie === yourMovie)
    printMovies(emptyList())
    Logger.log()
}