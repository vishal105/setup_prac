package com.example.setup_prac.data.remote.api

sealed class ResourceHandling<T>(
    val data: T?= null,
    val message:String? = null,
    val status:String? = null
){
     class Success<T>(data: T?,message: String) : ResourceHandling<T>(data,message)
     class ErrorState<T>(data: T?,message: String) : ResourceHandling<T>(data,message)
     class Loading<T>(message: String) : ResourceHandling<T>(message = message)

    override fun toString(): String {
        return when(this){
            is Success -> "Success with data = $data"
            is ErrorState -> "Error with message = $message"
            is Loading -> "Loading"
        }
    }
}