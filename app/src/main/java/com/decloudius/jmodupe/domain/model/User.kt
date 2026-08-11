package com.decloudius.jmodupe.domain.model

data class User(
    val id: Int = 0,
    val email: String,
    val password: String,
    val name: String,
    val ktp: String,
    val phone: String
)
