package com.islam.pureApp.domain.usecases

import com.islam.pureApp.domain.WrapperDataResult
import com.islam.pureApp.domain.entites.Word
import com.islam.pureApp.domain.repositories.GetWordsRepository
import org.junit.Assert.*

import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations

class MapWordsToWordListUseCaseTest {

    @Mock
    private lateinit var mapper: WordsToWordListMapper

    @Mock
    private lateinit var repository: GetWordsRepository

    private lateinit var useCase : MapWordsToWordListUseCase

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        useCase = MapWordsToWordListUseCase(repository, mapper)
    }

    @Test
    fun `when repository return LocalWordList result then wordList`() {
        val result = WrapperDataResult.LocalWordList(listOf())
        `when`(repository.getWords()).thenReturn(result)

        val expected = result.wordList
        val actual = useCase.execute()

        assertEquals(actual, expected)
    }

    @Test
    fun `when repository return RemoteWords result then wordList`() {
        val body = "<html>word</html>"
        val expected = listOf(Word(1, "word", 3))
        val result = WrapperDataResult.RemoteWords(body)
        `when`(repository.getWords()).thenReturn(result)
        `when`(mapper.map(body)).thenReturn(expected)

        val actual = useCase.execute()

        assertEquals(actual, expected)
    }

}