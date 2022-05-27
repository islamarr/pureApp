package com.islam.pureApp.domain

import com.islam.pureApp.domain.entites.Word

sealed class DataResult{
    data class RemoteWords(val allWords: String): DataResult()
    data class LocalWordList(val wordList: List<Word>): DataResult()
}
