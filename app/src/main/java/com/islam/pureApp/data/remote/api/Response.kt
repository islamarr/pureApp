package com.islam.pureApp.data.remote.api

sealed class Response<out T> {
    data class Success<out T>(val code: Int? = null, val value: T) : Response<T>()
    data class Error(val code: Int? = null, val message: String? = null) : Response<Nothing>()
    object NetworkError : Response<Nothing>()
    object EmptyError : Response<Nothing>()
}