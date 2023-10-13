package com.example.model.data.dataClasses

import kotlinx.serialization.Serializable

@Serializable
data class User(
    val user_id : Int ,
    val first_name   : String,
    val last_name    : String,
    val phone_number : Int,
    val password     : String
)