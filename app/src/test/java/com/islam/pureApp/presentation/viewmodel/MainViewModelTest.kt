package com.islam.pureApp.presentation.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.islam.pureApp.domain.entites.Word
import com.islam.pureApp.domain.usecases.MapWordsToWordListUseCase
import com.islam.pureApp.getOrAwaitValue
import org.junit.Assert.*

import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class MainViewModelTest {

    private lateinit var viewModel: MainViewModel

    @Mock
    private lateinit var useCase: MapWordsToWordListUseCase

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var list: List<Word>

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        viewModel = MainViewModel(useCase)

        list = listOf(Word(1, "word", 3), Word(2, "word2", 1), Word(3, "word3", 5))
        Mockito.`when`(useCase.execute()).thenReturn(list)
        viewModel.loadWordList()
    }

    @Test
    fun getWordsList() {
        val expected = list
        val actual = viewModel.wordsList.getOrAwaitValue()

        assertEquals(actual, expected)
    }

    @Test
    fun sortList_DEFAULT() {
        viewModel.wordsList.getOrAwaitValue()
        viewModel.sortList(SortType.DEFAULT)

        val actual = viewModel.wordsList.getOrAwaitValue()
        val expected = list

        assertEquals(actual, expected)
    }

    @Test
    fun sortList_ASCEND() {
        viewModel.wordsList.getOrAwaitValue()
        viewModel.sortList(SortType.ASCEND)

        val actual = viewModel.wordsList.getOrAwaitValue()
        val expected = listOf(Word(2, "word2", 1), Word(1, "word", 3), Word(3, "word3", 5))

        assertEquals(actual, expected)
    }

    @Test
    fun sortList_DESCEND() {
        viewModel.wordsList.getOrAwaitValue()
        viewModel.sortList(SortType.DESCEND)

        val actual = viewModel.wordsList.getOrAwaitValue()
        val expected = listOf(Word(3, "word3", 5), Word(1, "word", 3), Word(2, "word2", 1))

        assertEquals(actual, expected)
    }

    @Test
    fun searchList_EmptyQuery() {
        viewModel.wordsList.getOrAwaitValue()
        viewModel.searchInList("")

        val actual = viewModel.wordsList.getOrAwaitValue()
        val expected = list

        assertEquals(actual, expected)
    }

    @Test
    fun searchList_ExistQuery() {
        viewModel.wordsList.getOrAwaitValue()
        viewModel.searchInList("word")

        val actual = viewModel.wordsList.getOrAwaitValue()
        val expected = listOf(Word(1, "word", 3))

        assertEquals(actual, expected)
    }

    @Test
    fun searchList_ExistQuery_WithDifferentCase() {
        viewModel.wordsList.getOrAwaitValue()
        viewModel.searchInList("WORD")

        val actual = viewModel.wordsList.getOrAwaitValue()
        val expected = listOf(Word(1, "word", 3))

        assertEquals(actual, expected)
    }

    @Test
    fun searchList_NotExistQuery() {
        viewModel.wordsList.getOrAwaitValue()
        viewModel.searchInList("WD")

        val actual = viewModel.wordsList.getOrAwaitValue()
        val expected = listOf<Word>()

        assertEquals(actual, expected)
    }

}