package com.islam.pureApp.domain.usecases

import android.util.Log
import com.islam.pureApp.domain.entites.Word
import org.jsoup.Jsoup

class WordsToWordListMapper {
    private val regex = "[^A-Za-z]".toRegex()

    fun map(body: String): List<Word> {
        val text = Jsoup.parse(body).body().text()
        val listOfWords = arrayListOf<Word>()
        val resMap = mutableMapOf<String, Int>()
        text.trim().split(" ").forEach { word ->
            if (word.isNotEmpty()) {
                val key = regex.replace(word, "")
                resMap[key] = resMap[word]?.plus(1) ?: 1
            }
        }

        resMap.onEachIndexed { index, entry ->
            listOfWords.add(
                Word(
                    id = index, text = entry.key,
                    count = entry.value
                )
            )
        }
        return listOfWords
    }
}