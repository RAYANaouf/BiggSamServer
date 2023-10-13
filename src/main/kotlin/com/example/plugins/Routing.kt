package com.example.plugins

import com.example.model.data.dataClasses.User
import com.example.model.network.ApiHeaders
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.ktorm.database.Database

fun Application.configureRouting(database  : Database) {

    routing {

        var user : User? = null

        //postgres://RAYANaouf:whqo65NdXMze@ep-red-cherry-13821704.us-west-2.aws.neon.tech/neondb

        get("/") {
            call.respondText("hello BiggSam")
        }

        get("/signIn"){
            var email = call.request.headers[ApiHeaders.email]
            var password = call.request.headers[ApiHeaders.password]

            call.respond(
                User(
                    user_id = 0,
                    first_name = "rayan",
                    last_name = "aouf",
                    phone_number = 0,
                    password = "$password"
                )
            )
        }


        post("/getUsers"){
            user = call.receive<User>()

            println(user)


            call.respondText("hello  ${user?.first_name} ${user?.last_name}")
        }

        get("/getUser"){
            call.respondText("hello  ${user?.first_name} ${user?.last_name}")
        }

    }

}
