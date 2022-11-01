package com.example.kdp.chapter03

interface StarTrekRepository {
    fun getCaptain(starshipName: String): String
    fun addCaptain(starshipName: String, captainName: String)
}

class DefaultStarTrekRepository: StarTrekRepository {
    private val starshipCaptains = mutableMapOf("USS Enter prise" to "Jean-Luc Picard")

    override fun getCaptain(starshipName: String) = starshipCaptains[starshipName] ?: "Unknown"

    override fun addCaptain(starshipName: String, captainName: String) {
        starshipCaptains[starshipName] = captainName
    }
}

class LoggingGetCaptain(private val repository: StarTrekRepository): StarTrekRepository by repository {
    override fun getCaptain(starshipName: String): String {
        println("Getting captain for $starshipName")
        return repository.getCaptain(starshipName)
    }
}

class ValidatingAdd(private val repository: StarTrekRepository): StarTrekRepository by repository {
    private val maxNameLength = 15

    override fun addCaptain(starshipName: String, captainName: String) {
        require(captainName.length < maxNameLength) {
            "$captainName name is longer than $maxNameLength characters!"
        }
        repository.addCaptain(starshipName, captainName)
    }
}

fun main() {
    val starTrekRepository = DefaultStarTrekRepository()
    val withValidating = ValidatingAdd(starTrekRepository)
    val withLoggingAndValidating = LoggingGetCaptain(withValidating)
    withLoggingAndValidating.getCaptain("USS Enterprise")
    withLoggingAndValidating.addCaptain("USS Voyager", "Kathryn Janeway")
}