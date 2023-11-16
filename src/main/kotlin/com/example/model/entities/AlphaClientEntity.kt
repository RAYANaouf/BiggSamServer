package com.example.model.entities

import org.ktorm.schema.Table
import org.ktorm.schema.int
import org.ktorm.schema.varchar

object AlphaClientEntity : Table<Nothing>("alphaclient") {

    val client_id       = int("client_age").primaryKey()
    val client_fname    = varchar("client_fname")
    val client_lname    = varchar("client_lname")
    val client_age      = int("client_age")
    val client_number   = int("client_number")
    val client_sex      = varchar("client_sex")
    val client_photo    = varchar("client_photo")
    val client_email    = varchar("client_email")
    val client_password = varchar("client_password")

}