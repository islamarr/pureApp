package com.islam.pureApp.domain

import com.islam.pureApp.domain.entites.Word

sealed class WrapperDataResult {
    data class RemoteWords(val allWords: String) : WrapperDataResult()
    data class LocalWordList(val wordList: List<Word>) : WrapperDataResult()
}
