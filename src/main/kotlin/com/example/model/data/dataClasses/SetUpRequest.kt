package com.example.model.data.dataClasses

import kotlinx.serialization.Serializable


@Serializable
data class SetUpRequest(
    val user_email  : String,
    val user_photo  : String? = null ,
    val accountType : String ,
    )