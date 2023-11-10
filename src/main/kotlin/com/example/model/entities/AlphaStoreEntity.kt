package com.example.model.entities

import org.ktorm.schema.Table
import org.ktorm.schema.int
import org.ktorm.schema.varchar

object AlphaStoreEntity : Table<Nothing>("AlphaStore") {

    val store_id        = int("store_id").primaryKey()
    val store_name      = varchar("store_name")
    val store_photo     = varchar("store_photo")
    val store_email     = varchar("store_email")
    val store_password  = varchar("password")
    val store_number    = int("store_number")
    val store_wilaya    = varchar("store_wilaya")

}