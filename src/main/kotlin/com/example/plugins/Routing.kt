package com.example.plugins

import com.example.model.data.dataClasses.AlphaClient
import com.example.model.data.dataClasses.AlphaStore
import com.example.model.data.dataClasses.SetUpRequest
import com.example.model.data.sealedClasses.ApiExceptions
import com.example.model.entities.AlphaClientEntity
import com.example.model.entities.AlphaStoreEntity
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


        //postgres://RAYANaouf:whqo65NdXMze@ep-red-cherry-13821704.us-west-2.aws.neon.tech/neondb

        get("/") {
            call.respondText("hello BiggSam v0.25")
        }

        /****************   store    *****************/

        get(ApiRoutes.storeSignIn){
            var email = call.request.headers[ApiHeaders.email]
            var password = call.request.headers[ApiHeaders.password]


            var stores = database
                .from(AlphaStoreEntity)
                .select()
                .where {
                    (AlphaStoreEntity.store_email like "$email") and (AlphaStoreEntity.store_password like "$password")
                }



            for (store in stores ){
                if (store.size() > 0){

                    call.response.headers.append(ApiHeaders.exception , ApiExceptions.NoException().msg)

                    call.respond(
                        AlphaStore(
                            store_id       = store[AlphaStoreEntity.store_id]?.toLong() ?: 0,
                            store_name     = store[AlphaStoreEntity.store_name]     ?: "",
                            store_email    = store[AlphaStoreEntity.store_email]    ?: "",
                            store_photo    = store[AlphaStoreEntity.store_photo]    ?: "",
                            store_number   = store[AlphaStoreEntity.store_number]   ?: 0 ,
                            store_wilaya   = store[AlphaStoreEntity.store_wilaya]   ?: "",
                            store_password = store[AlphaStoreEntity.store_password] ?: ""
                        )
                    )
                }
                else{
                    call.response.headers.append(ApiHeaders.exception , ApiExceptions.WrongEmailOrPasswordException().msg)
                    call.respond("error")
                }
            }


        }

        post(ApiRoutes.storeSignUp){

            val store = call.receive<AlphaStore>()

            var result = database.insert(AlphaStoreEntity){
                set(it.store_name     , store.store_name)
                set(it.store_number   , store.store_number)
                set(it.store_password , store.store_password)
                set(it.store_email    , store.store_email)
                set(it.store_wilaya   , store.store_wilaya)
                set(it.store_photo    , store.store_photo)
            }

            if (result > 0){
                call.response.headers.append(ApiHeaders.exception , ApiExceptions.NoException().msg)
                call.response.headers.append(ApiHeaders.createdId , result.toString())
            }
            else{
                call.response.headers.append(ApiHeaders.exception , ApiExceptions.NoException().msg)
            }




        }

        /***************   client    *****************/

        get(ApiRoutes.clientSignIn){
            val email    = call.request.headers[ApiHeaders.email]
            val password = call.request.headers[ApiHeaders.password]

            val clients = arrayListOf<AlphaClient>()

            val clientsQuery = database
                .from(AlphaClientEntity)
                .select()



            for (client in clientsQuery){

                clients.add(
                    AlphaClient(
                        client_id       = client[AlphaClientEntity.client_id    ]?.toLong() ?: 0,
                        client_fname    = client[AlphaClientEntity.client_fname ] ?: "",
                        client_lname    = client[AlphaClientEntity.client_lname ] ?: "",
                        client_age      = client[AlphaClientEntity.client_age   ] ?: 0,
                        client_number   = client[AlphaClientEntity.client_number] ?: 0,
                        client_sex      = client[AlphaClientEntity.client_sex   ] ?: "",
                        client_photo    = client[AlphaClientEntity.client_photo ] ?: "",
                        client_email    = client[AlphaClientEntity.client_email ] ?: "",
                        client_password = client[AlphaClientEntity.client_password] ?: ""
                    )
                )

            }

            if (clients.isNotEmpty()){
                call.response.headers.append(ApiHeaders.exception , ApiExceptions.NoException().msg)
                call.respond(clients[0])
            }
            else{
                call.response.headers.append(ApiHeaders.exception , ApiExceptions.WrongEmailOrPasswordException().msg)
                call.respond("error")
            }

            println("email : $email \n password : $password")

        }


        get(ApiRoutes.getStores){

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
