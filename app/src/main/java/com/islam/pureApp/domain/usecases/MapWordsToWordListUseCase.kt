package com.islam.pureApp.domain.usecases

import com.islam.pureApp.domain.WrapperDataResult
import com.islam.pureApp.domain.entites.Word
import com.islam.pureApp.domain.repositories.GetWordsRepository

class MapWordsToWordListUseCase(
    private val repository: GetWordsRepository,
    private val wordsToWordListMapper: WordsToWordListMapper
) {
    fun execute(): List<Word> {
        return when (val result = repository.getWords()) {
            is WrapperDataResult.LocalWordList -> result.wordList
            is WrapperDataResult.RemoteWords -> {
                val list = wordsToWordListMapper.map(result.allWords)
                repository.clearCacheList()
                repository.cacheWordList(list)
                list
            }
        }
    }

}