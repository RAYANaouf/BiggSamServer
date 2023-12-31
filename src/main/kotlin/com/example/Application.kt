package com.example

import com.example.plugins.*
import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import io.ktor.server.plugins.contentnegotiation.*
import org.ktorm.database.Database

fun main() {
    embeddedServer(Netty, port = 8080, host = "0.0.0.0"){

        install(ContentNegotiation){
            json()
        }

        module()

    }.start(wait = true)

}



fun Application.module() {

    var database = Database.connect(
        url = "jdbc:postgresql://ep-red-cherry-13821704.us-west-2.aws.neon.tech/neondb",
        driver = "org.postgresql.Driver",
        user = "RAYANaouf",
        password = "whqo65NdXMze"
    )

//    configureSecurity()
    configureRouting(database)
}




