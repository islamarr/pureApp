package com.islam.pureApp.data.remote.repositories

import com.islam.pureApp.data.db.datasource.GetWordsLocalDataSource
import com.islam.pureApp.data.remote.api.NetworkResponse
import com.islam.pureApp.data.remote.datasource.GetWordsRemoteDataSource
import com.islam.pureApp.domain.WrapperDataResult
import com.islam.pureApp.domain.entites.Word
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class GetWordsRepositoryImplTest {

    private lateinit var repository: GetWordsRepositoryImpl

    @Mock
    private lateinit var remoteDataSource: GetWordsRemoteDataSource

    @Mock
    private lateinit var localDataSource: GetWordsLocalDataSource

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        repository = GetWordsRepositoryImpl(remoteDataSource, localDataSource)
    }

    @Test
    fun `when get words from remote service successfully return Result from remote`(){
        val response = "word"
        val networkResponse = NetworkResponse.Success(response)
        Mockito.`when`(remoteDataSource.getWordsResponse()).thenReturn(networkResponse)

        val expected = WrapperDataResult.RemoteWords(response)
        val actual = repository.getWords()

        Assert.assertEquals(actual, expected)
    }

    @Test
    fun `when remote response is null return Result from local`(){
        val result = listOf<Word>()
        val networkResponse = NetworkResponse.Success<String>(null)
        Mockito.`when`(remoteDataSource.getWordsResponse()).thenReturn(networkResponse)
        Mockito.`when`(localDataSource.getAllWords()).thenReturn(result)

        val expected = WrapperDataResult.LocalWordList(result)
        val actual = repository.getWords()

        Assert.assertEquals(actual, expected)
    }

    @Test
    fun `when remote response is failure return Result from local`(){
        val result = listOf<Word>()
        val networkResponse = NetworkResponse.Failure<String>("")
        Mockito.`when`(remoteDataSource.getWordsResponse()).thenReturn(networkResponse)
        Mockito.`when`(localDataSource.getAllWords()).thenReturn(result)

        val expected =WrapperDataResult.LocalWordList(result)
        val actual = repository.getWords()

        Assert.assertEquals(actual, expected)
    }

}