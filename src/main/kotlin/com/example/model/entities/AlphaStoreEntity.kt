package com.example.model.entities

import org.ktorm.schema.Table
import org.ktorm.schema.int
import org.ktorm.schema.varchar

object AlphaStoreEntity : Table<Nothing>("AlphaStore") {

    val store_id   = int("store_id").primaryKey()
    val store_name = varchar("store_name")
    val store_img  = varchar("store_profile_img")

}