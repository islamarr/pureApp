package com.islam.pureApp.domain.usecases

import com.islam.pureApp.domain.entites.Word
import org.junit.Assert.assertEquals
import org.junit.Test

class WordsToWordListMapperTest {

    private val wordsToWordListMapper = WordsToWordListMapper()

    @Test
    fun `when map html body return list of word objects`() {
        val body = "<html>word word2 word3 word word2 word /< $ #@ </html>"
        val actual = wordsToWordListMapper.map(body)
        val expected = listOf(Word(1, "word", 3), Word(2, "word2", 2), Word(3, "word3", 1))

        assertEquals(actual, expected)
    }

    @Test
    fun `when map empty html body return empty list of word`() {
        val body = "<html></html>"
        val actual = wordsToWordListMapper.map(body)
        val expected = listOf<Word>()

        assertEquals(actual, expected)
    }

}