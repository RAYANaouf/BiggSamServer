package com.example.model.data.dataClasses

import kotlinx.serialization.Serializable

@Serializable
data class AlphaClient (
    val client_id       : Long = -1,
    val client_fname    : String,
    val client_lname    : String,
    val client_number   : Int = -1,
    val client_age      : Int = -1,
    val client_sex      : String = "",
    val client_photo    : String = "",
    val client_email    : String,
    val client_password : String
)