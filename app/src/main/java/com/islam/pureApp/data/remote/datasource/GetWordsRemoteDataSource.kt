package com.islam.pureApp.data.remote.datasource

import com.islam.pureApp.data.remote.api.NetworkResponse

interface GetWordsRemoteDataSource {
    fun getWordList(): NetworkResponse<String>
}