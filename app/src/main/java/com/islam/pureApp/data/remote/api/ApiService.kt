package com.islam.pureApp.data.remote.api

interface ApiService {
    fun getAllWords(): Response<String>
}