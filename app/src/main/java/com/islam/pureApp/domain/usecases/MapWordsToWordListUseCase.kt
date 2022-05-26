package com.islam.pureApp.domain.usecases

import com.islam.pureApp.domain.entites.Word
import com.islam.pureApp.domain.repositories.GetWordsRepository

class MapWordsToWordListUseCase(
    private val repository: GetWordsRepository,
    private val wordsToWordListMapper: WordsToWordListMapper
) {

    fun execute(): List<Word> {
        val allWords = repository.getWords()
        return wordsToWordListMapper.map(allWords)
    }
}