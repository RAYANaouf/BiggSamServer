package com.example.model.data.dataClasses

import kotlinx.serialization.Serializable

@Serializable
data class AlphaStore (
    val store_id       : Long = 0,
    val store_name     : String ,
    val store_photo    : String? = null,
    val store_email    : String ,
    val store_password : String ,
    val store_number   : Int    ,
    val store_wilaya   : String? = null
)