package com.example.model.data.dataClasses

import kotlinx.serialization.Serializable

@Serializable
data class user(
    val first_name   : String,
    val last_name    : String,
    val phone_number : Int,
    val user_name    : String,
    val password     : String
)