package com.example.model.data.dataClasses

import kotlinx.serialization.Serializable


@Serializable
data class SignUpRequest(
    val firstName : String,
    val lastName  : String,
    val email     : String,
    val wilaya    : String,
    val number    : String,
    val password  : String,
)