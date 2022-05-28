package com.islam.pureApp.domain.usecases

import com.islam.pureApp.domain.entites.Word
import org.jsoup.Jsoup

class WordsToWordListMapper {

    fun map(body: String): List<Word> {
        val text = Jsoup.parse(body).body().text()
        return text
            .split("\\b".toRegex())
            .filter { it.any { char -> char.isLetterOrDigit() } }
            .groupingBy { it }
            .eachCount()
            .entries
            .mapIndexed { index, textCount -> Word(index + 1, textCount.key, textCount.value) }
    }

}