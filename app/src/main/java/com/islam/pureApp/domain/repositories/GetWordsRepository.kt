package com.islam.pureApp.domain.repositories

import com.islam.pureApp.domain.DataResult
import com.islam.pureApp.domain.entites.Word

interface GetWordsRepository {
    fun getWords(): DataResult
    fun cacheWordList(words: List<Word>)
    fun clearCacheList()
}