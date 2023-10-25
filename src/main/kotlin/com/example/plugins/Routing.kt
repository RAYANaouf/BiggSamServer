package com.example.plugins

import com.example.model.data.dataClasses.SignUpRequest
import com.example.model.data.dataClasses.User
import com.example.model.data.sealedClasses.ApiExceptions
import com.example.model.entities.UserEntity
import com.example.model.network.ApiHeaders
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.ktorm.database.Database
import org.ktorm.dsl.*
import org.ktorm.dsl.like

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


            var users = database
                .from(UserEntity)
                .select()
                .where {
                    (UserEntity.user_email like "$email") and (UserEntity.user_password like "$password")
                }



            for (user in users ){
                if (user.size() > 0){

                    call.response.headers.append(ApiHeaders.exception , ApiExceptions.NoException().msg)

                    call.respond(
                        User(
                            user_id = user[UserEntity.user_id]!!,
                            first_name = user[UserEntity.first_name]!!,
                            last_name = user[UserEntity.last_name]!!,
                            phone_number = user[UserEntity.phone_number]!!,
                            password = user[UserEntity.user_password]!!,
                            user_email = user[UserEntity.user_email]!!
                        )
                    )
                }
                else{
                    call.response.headers.append(ApiHeaders.exception , ApiExceptions.WrongEmailOrPasswordException().msg)
                    call.respond("error")
                }
            }



        }

        post("/signUp"){

            val signUpRequest = call.receive<SignUpRequest>()

            database.insert(UserEntity){
                set(it.first_name    , signUpRequest.firstName)
                set(it.last_name     , signUpRequest.lastName)
                set(it.phone_number  , signUpRequest.number.toIntOrNull()?: 0)
                set(it.user_password , signUpRequest.password)
                set(it.user_email    , signUpRequest.email)
            }

            var users = database
                .from(UserEntity)
                .select()
                .where {
                    UserEntity.user_email like "${signUpRequest.email}"
                }

            for (user in users){
                if (user.size() > 0){

                    call.response.headers.append(ApiHeaders.exception , ApiExceptions.NoException().msg)

                    call.respond(
                        User(
                            user_id = user[UserEntity.user_id]!!,
                            first_name = user[UserEntity.first_name]!!,
                            last_name = user[UserEntity.last_name]!!,
                            phone_number = user[UserEntity.phone_number]!!,
                            password = user[UserEntity.user_password]!!,
                            user_email = user[UserEntity.user_email]!!
                        )
                    )
                }
                else{
//                    call.response.headers.append(ApiHeaders.exception , ApiExceptions.WrongEmailOrPasswordException().msg)
                    call.respond("error")
                }
            }
            

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
