package com.example.model.data.dataClasses

import kotlinx.serialization.Serializable

@Serializable
data class AlphaStore(
    val store_id   : Long = 0 ,
    val store_name : String   ,
    val store_img  : String
)