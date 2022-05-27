package com.islam.pureApp.domain.repositories

import com.islam.pureApp.domain.WrapperDataResult
import com.islam.pureApp.domain.entites.Word

interface GetWordsRepository {
    fun getWords(): WrapperDataResult
    fun cacheWordList(words: List<Word>)
    fun clearCacheList()
}