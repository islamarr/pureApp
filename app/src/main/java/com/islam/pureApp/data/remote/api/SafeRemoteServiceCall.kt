package com.islam.pureApp.data.remote.api

import java.io.IOException

/**
 * To handle Http Exceptions Like no internet connection and Time out
 */
interface SafeRemoteServiceCall {

    fun <T> safeApiCall(
        apiCall: () -> T
    ): NetworkResponse<T> {
        return try {
            NetworkResponse.Success(apiCall.invoke())
        } catch (throwable: Throwable) {
            when (throwable) {
                is IOException -> {
                    NetworkResponse.Failure(
                        0,
                        throwable.localizedMessage
                    )
                }
                else -> {
                    NetworkResponse.Failure(0, throwable.localizedMessage)
                }
            }
        }

    }

}