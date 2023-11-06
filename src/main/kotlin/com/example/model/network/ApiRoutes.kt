package com.example.model.network


object ApiRoutes {
    private const val BASE_URL = "https://biggsamserver.onrender.com"
    const val getUsers  = "$BASE_URL/getUser"
    const val signIn    = "$BASE_URL/signIn"
    const val signUp    = "$BASE_URL/signUp"
    const val setUp     = "$BASE_URL/setUpAccount"
    const val getStores = "$BASE_URL/getStores"
}