package com.islam.pureApp.data.remote.repositories

import com.islam.pureApp.data.db.datasource.GetWordsLocalDataSource
import com.islam.pureApp.data.remote.api.NetworkResponse
import com.islam.pureApp.data.remote.datasource.GetWordsRemoteDataSource
import com.islam.pureApp.domain.WrapperDataResult
import com.islam.pureApp.domain.entites.Word
import com.islam.pureApp.domain.repositories.GetWordsRepository

class GetWordsRepositoryImpl(
    private val remoteDataSource: GetWordsRemoteDataSource,
    private val localDataSource: GetWordsLocalDataSource
) :
    GetWordsRepository {

    override fun getWords(): WrapperDataResult {
        return when (val res = remoteDataSource.getWordsResponse()) {
            is NetworkResponse.Failure -> WrapperDataResult.LocalWordList(localDataSource.getAllWords())
            is NetworkResponse.Success -> {
                res.data?.let {
                    WrapperDataResult.RemoteWords(it)
                } ?: WrapperDataResult.LocalWordList(localDataSource.getAllWords())
            }
        }
    }

    override fun cacheWordList(words: List<Word>) {
        localDataSource.insertAllWords(words)
    }

    override fun clearCacheList() {
        localDataSource.clearAll()
    }
}