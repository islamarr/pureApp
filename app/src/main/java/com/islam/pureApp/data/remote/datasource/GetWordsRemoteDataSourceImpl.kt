package com.islam.pureApp.data.remote.datasource

import com.islam.pureApp.data.remote.api.ApiService
import com.islam.pureApp.data.remote.api.NetworkResponse
import com.islam.pureApp.data.remote.api.Response

class GetWordsRemoteDataSourceImpl(private val apiService: ApiService) : GetWordsRemoteDataSource {
    override fun getWordsResponse(): NetworkResponse<String> {

        return when (val networkResult = apiService.getAllWords()) {
            is Response.Success -> {
                NetworkResponse.Success(networkResult.value)
            }
            is Response.Error -> {
                NetworkResponse.Failure(networkResult.message)
            }
            is Response.EmptyError -> NetworkResponse.Failure("")
            is Response.NetworkError -> NetworkResponse.Failure(null)
        }
    }
}