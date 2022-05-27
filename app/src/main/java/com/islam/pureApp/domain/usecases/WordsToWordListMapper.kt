package com.islam.pureApp.domain.usecases

import com.islam.pureApp.domain.entites.Word
import org.jsoup.Jsoup

class WordsToWordListMapper {

    fun map(body: String): List<Word> {
        val regex = "[^A-Za-z]".toRegex()
        val text = Jsoup.parse(body).body().text()

        val wordWithCountMap = createWordsMap(text, regex)
        return createWordList(wordWithCountMap)
    }

    private fun createWordsMap(
        text: String,
        regex: Regex,
    ): MutableMap<String, Int> {
        val wordWithCountMap = mutableMapOf<String, Int>()
        text.trim().split(" ").forEach { word ->
            if (word.isNotEmpty() && word.isNotBlank()) {
                val key = regex.replace(word, "")
                wordWithCountMap[key] = wordWithCountMap[word]?.plus(1) ?: 1
            }
        }
        return wordWithCountMap
    }

    private fun createWordList(wordWithCountMap: MutableMap<String, Int>): ArrayList<Word> {
        val wordList = arrayListOf<Word>()
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