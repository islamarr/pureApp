package com.islam.pureApp.data.db.datasource

import androidx.test.platform.app.InstrumentationRegistry
import com.islam.pureApp.domain.entites.Word
import junit.framework.TestCase
import org.hamcrest.CoreMatchers
import org.hamcrest.MatcherAssert
import org.junit.Assert.*

import org.junit.After
import org.junit.Before
import org.junit.Test

class GetWordsLocalDataSourceImplTest {

    private lateinit var localDataSource: GetWordsLocalDataSourceImpl

    @Before
    fun setUp() {
        localDataSource =
            GetWordsLocalDataSourceImpl(InstrumentationRegistry.getInstrumentation().targetContext)
    }

    @After
    fun tearDown() {
        localDataSource.closeDB()
    }

    @Test
    fun clearAllRows_sizeEqualZero() {
        localDataSource.clearAll()
        val data = localDataSource.getAllWords()
        MatcherAssert.assertThat(data.size, CoreMatchers.`is`(0))
    }

    @Test
    fun checkInsertOneRow() {
        val list = listOf(Word(text = "and", count = 48))
        localDataSource.clearAll()
        localDataSource.insertAllWords(list)

        val data = localDataSource.getAllWords()
        MatcherAssert.assertThat(data.size, CoreMatchers.`is`(1))
        assertEquals("and", data[0].text)
        assertEquals(48, data[0].count)
        assertEquals(1, data[0].id)

    }

}