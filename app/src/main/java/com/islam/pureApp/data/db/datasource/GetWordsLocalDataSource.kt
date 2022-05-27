package com.islam.pureApp.data.db.datasource

import com.islam.pureApp.domain.entites.Word

interface GetWordsLocalDataSource {
    fun getAllWords(): List<Word>
    fun insertAllWords(words: List<Word>)
    fun clearAll()
}