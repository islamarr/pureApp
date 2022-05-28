package com.islam.pureApp.data.remote.datasource

import com.islam.pureApp.data.remote.api.ApiService
import com.islam.pureApp.data.remote.api.NetworkResponse
import com.islam.pureApp.data.remote.api.Response
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class GetWordsRemoteDataSourceImplTest {

    private lateinit var remoteDataSource: GetWordsRemoteDataSourceImpl

    @Mock
    private lateinit var apiService: ApiService

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        remoteDataSource = GetWordsRemoteDataSourceImpl(apiService)
    }

    @Test
    fun `when response is success return the right NetworkResponse`() {
        val networkResponse = Response.Success(200, "response")
        Mockito.`when`(apiService.getAllWords()).thenReturn(networkResponse)

        val actual = remoteDataSource.getWordsResponse()
        val expected = NetworkResponse.Success(networkResponse.value)

        assertEquals(actual, expected)
    }

    @Test
    fun `when response has Error return Failure NetworkResponse with error message`() {
        val networkResponse = Response.Error(400, "error")
        Mockito.`when`(apiService.getAllWords()).thenReturn(networkResponse)

        val actual = remoteDataSource.getWordsResponse()
        val expected = NetworkResponse.Failure<String>(networkResponse.message)

        assertEquals(actual, expected)
    }

    @Test
    fun `when response has EmptyError return Failure NetworkResponse with empty message`() {
        val networkResponse = Response.EmptyError
        Mockito.`when`(apiService.getAllWords()).thenReturn(networkResponse)

        val actual = remoteDataSource.getWordsResponse()
        val expected = NetworkResponse.Failure<String>("")

        assertEquals(actual, expected)
    }

    @Test
    fun `when response has NetworkError return Failure NetworkResponse with null message`() {
        val networkResponse = Response.NetworkError
        Mockito.`when`(apiService.getAllWords()).thenReturn(networkResponse)

        val actual = remoteDataSource.getWordsResponse()
        val expected = NetworkResponse.Failure<String>(null)

        assertEquals(actual, expected)
    }

}