package com.example.plugins

import com.example.model.data.dataClasses.Alpha1User
import com.example.model.data.dataClasses.AlphaStore
import com.example.model.data.dataClasses.SetUpRequest
import com.example.model.data.dataClasses.SignUpRequest
import com.example.model.data.sealedClasses.ApiExceptions
import com.example.model.entities.AlphaStoreEntity
import com.example.model.entities.UserEntity
import com.example.model.network.ApiHeaders
import com.example.model.network.ApiRoutes
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.ktorm.database.Database
import org.ktorm.dsl.*
import org.ktorm.dsl.like

fun Application.configureRouting(database  : Database) {

    routing {

        var user : Alpha1User? = null

        //postgres://RAYANaouf:whqo65NdXMze@ep-red-cherry-13821704.us-west-2.aws.neon.tech/neondb

        get("/") {
            call.respondText("hello BiggSam v0.25")
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
                        Alpha1User(
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
                set(it.img_uri       , "")
                set(it.account_type  , "")
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
                        Alpha1User(
                            user_id = user[UserEntity.user_id]!!,
                            first_name = user[UserEntity.first_name]!!,
                            last_name = user[UserEntity.last_name]!!,
                            phone_number = user[UserEntity.phone_number]!!,
                            password = user[UserEntity.user_password]!!,
                            user_email = user[UserEntity.user_email]!!,
                            img_uri = user[UserEntity.img_uri],
                            account_type = user[UserEntity.account_type]
                        )
                    )
                }
                else{
                    call.response.headers.append(ApiHeaders.exception , ApiExceptions.WrongEmailOrPasswordException().msg)
                    call.respond("error")
                }
            }
            

        }

        post("/setUpAccount") {

            var setUpAccount = call.receive<SetUpRequest>()

            database.update(UserEntity){
                set(it.img_uri , setUpAccount.user_photo)
                set(it.account_type , setUpAccount.accountType)

                where { it.user_email like setUpAccount.user_email }
            }

        }


        post("/getUsers"){
            user = call.receive<Alpha1User>()

            println(user)


            call.respondText("hello  ${user?.first_name} ${user?.last_name}")
        }

        get("/getUser"){
            call.respondText("hello  ${user?.first_name} ${user?.last_name}")
        }

        get("/${ApiRoutes.getStores}"){

            val response = database
                .from(AlphaStoreEntity)
                .select()

            var stores  = ArrayList<AlphaStore>()

            for(store in response ){
                stores.add(
                    AlphaStore(
                        store_id       = store[AlphaStoreEntity.store_id]?.toLong() ?: 0 ,
                        store_name     = store[AlphaStoreEntity.store_name] ?:  "",
                        store_photo    = store[AlphaStoreEntity.store_photo] ?: "",
                        store_email    = store[AlphaStoreEntity.store_email] ?: "",
                        store_password = store[AlphaStoreEntity.store_password] ?: "",
                        store_number   = store[AlphaStoreEntity.store_number] ?: 0 ,
                        store_wilaya   = store[AlphaStoreEntity.store_wilaya] ?: ""
                    )
                )
            }


            call.response.headers.append(ApiHeaders.exception , ApiExceptions.NoException().msg)

            call.respond(stores)

        }

    }

}
