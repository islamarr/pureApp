package com.islam.pureApp.data.remote.datasource

import com.islam.pureApp.data.remote.api.NetworkResponse
import com.islam.pureApp.data.remote.api.Response
import com.islam.pureApp.data.remote.api.SafeRemoteServiceCall
import com.islam.pureApp.domain.entites.Word

interface GetWordsRemoteDataSource : SafeRemoteServiceCall {
    fun getWordList(): NetworkResponse<Response<List<Word>>>
}