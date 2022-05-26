package com.islam.pureApp.domain.usecases

import com.islam.pureApp.domain.entites.Word
import com.islam.pureApp.domain.repositories.GetWordsRepository

class GetWordListUseCase(private val repository: GetWordsRepository) {

    fun execute(): List<Word> {
        return WordsToWordListMapper().map(repository.getWordList())
    }
}