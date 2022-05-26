package com.islam.pureApp.data.remote.repositories

import com.islam.pureApp.data.remote.api.NetworkResponse
import com.islam.pureApp.data.remote.datasource.GetWordsRemoteDataSource
import com.islam.pureApp.domain.entites.Word
import com.islam.pureApp.domain.repositories.GetWordsRepository
import com.islam.pureApp.domain.usecases.WordsToWordListMapper

class GetWordsRepositoryImpl(private val getWordsRemoteDataSource: GetWordsRemoteDataSource) :
    GetWordsRepository {
    override fun getWordList(): String {
        return when (val res = getWordsRemoteDataSource.getWordList()) {
            is NetworkResponse.Failure -> res.reason!!
            is NetworkResponse.Success -> {
                res.data!!
            }
        }
    }
}