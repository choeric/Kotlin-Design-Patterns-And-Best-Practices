package com.example.kdp.chapter02

enum class Role {
    ADMIN, SUPER_ADMIN, REGULAR_USE
}

data class User(
    val name: String,
    val role: Role,
    val permissions: Set<String>
//    val tasks: List<String>
){
    fun hasPermission(permission: String) = permission in permissions
}

val allUsers = mutableListOf<User>()

fun createUser(_name: String, role: Role) {
    for (u in allUsers) {
        if (u.role == role) {
            allUsers += u.copy(name = _name)
            return
        }

    }
}

fun main() {
    val originalUser = User("admin", Role.ADMIN, setOf("R","W", "D"))
    allUsers += originalUser
    createUser("admin2", Role.ADMIN)
    println(allUsers)
}