package com.islam.pureApp.domain.usecases

import com.islam.pureApp.domain.entites.Word
import org.jsoup.Jsoup

class WordsToWordListMapper {

    private val regex = "[^A-Za-z]".toRegex()
    private val wordList = arrayListOf<Word>()
    private val wordWithCountMap = mutableMapOf<String, Int>()

    fun map(body: String): List<Word> {
        val text = Jsoup.parse(body).body().text()
        text.trim().split(" ").forEach { word ->
            if (word.isNotEmpty() && word.isNotBlank()) {
                val key = regex.replace(word, "")
                wordWithCountMap[key] = wordWithCountMap[word]?.plus(1) ?: 1
            }
        }

        wordWithCountMap.onEachIndexed { index, entry ->
            wordList.add(
                Word(
                    id = index, text = entry.key,
                    count = entry.value
                )
            )
        }

        return wordList
    }
}