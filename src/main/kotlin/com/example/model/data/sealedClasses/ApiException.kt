package com.example.model.data.sealedClasses

sealed class ApiExceptions(val msg: String) : Exception() {

    //api
    class NoException                  : ApiExceptions(msg = "no Exception")
    class WrongEmailOrPasswordException : ApiExceptions(msg = "your email or password is wrong")

}
