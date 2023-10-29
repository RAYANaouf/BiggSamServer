package com.example.model.entities

import org.ktorm.schema.Table
import org.ktorm.schema.int
import org.ktorm.schema.varchar

object UserEntity : Table<Nothing>("Alpha1User"){
    val user_id        = int("user_id").primaryKey()
    val first_name     = varchar("first_name")
    val last_name      = varchar("last_name")
    val phone_number   = int("phone_number")
    val user_email     = varchar("user_email")
    val user_password  = varchar("user_password")
    val img_uri        = varchar("img_uri")
    val account_type   = varchar("account_type")
}