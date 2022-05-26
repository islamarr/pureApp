package com.islam.pureApp.data.remote.api


sealed class NetworkResponse<T> {
    data class Success<T>(
        val data: T?,
    ) : NetworkResponse<T>()

    data class Failure<T>(
        val reason: String? = null,
    ) : NetworkResponse<T>()
}