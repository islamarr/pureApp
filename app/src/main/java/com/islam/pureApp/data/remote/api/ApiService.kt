package com.islam.pureApp.data.remote.api

import com.islam.pureApp.domain.entites.Word

interface ApiService {
    fun getWords(): Response<List<Word>>
}