package com.islam.pureApp.data.remote.repositories

import com.islam.pureApp.data.db.datasource.GetWordsLocalDataSource
import com.islam.pureApp.data.remote.api.NetworkResponse
import com.islam.pureApp.data.remote.datasource.GetWordsRemoteDataSource
import com.islam.pureApp.domain.DataResult
import com.islam.pureApp.domain.entites.Word
import com.islam.pureApp.domain.repositories.GetWordsRepository
import com.islam.pureApp.domain.usecases.WordsToWordListMapper

class GetWordsRepositoryImpl(
    private val getWordsRemoteDataSource: GetWordsRemoteDataSource,
    private val localDataSource: GetWordsLocalDataSource
) :
    GetWordsRepository {
    override fun getWords(): DataResult {
        return when (val res = getWordsRemoteDataSource.getWordsResponse()) {
            is NetworkResponse.Failure -> DataResult.LocalWordList(localDataSource.getAllWords())
            is NetworkResponse.Success -> {
                res.data?.let {
                    DataResult.RemoteWords(it)
                } ?: DataResult.LocalWordList(localDataSource.getAllWords())
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