package com.example.model.data.dataClasses

import kotlinx.serialization.Serializable

@Serializable
data class Alpha1User(
    val user_id      : Int,
    val first_name   : String,
    val last_name    : String,
    val user_email   : String,
    val phone_number : Int,
    val password     : String,
    val img_uri      : String? = null,
    val account_type : String? = null
)