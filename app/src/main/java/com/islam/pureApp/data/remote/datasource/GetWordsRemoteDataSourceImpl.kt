package com.islam.pureApp.data.remote.datasource

import com.islam.pureApp.data.remote.api.ApiService
import com.islam.pureApp.data.remote.api.NetworkResponse
import com.islam.pureApp.data.remote.api.Response
import com.islam.pureApp.domain.entites.Word

class GetWordsRemoteDataSourceImpl(private val apiService: ApiService) : GetWordsRemoteDataSource {
    override fun getWordList(): NetworkResponse<Response<List<Word>>> {
        return safeApiCall { apiService.getWords() }
    }
}