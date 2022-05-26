package com.islam.pureApp.di

import com.islam.pureApp.data.remote.api.ApiServiceImpl
import com.islam.pureApp.data.remote.datasource.GetWordsRemoteDataSourceImpl
import com.islam.pureApp.data.remote.repositories.GetWordsRepositoryImpl
import com.islam.pureApp.domain.usecases.GetWordListUseCase

class PureAppModule {

    val useCase: GetWordListUseCase by lazy {
        GetWordListUseCase(repository)
    }

    private val repository: GetWordsRepositoryImpl by lazy {
        GetWordsRepositoryImpl(remoteDataSource)
    }

    private val remoteDataSource: GetWordsRemoteDataSourceImpl by lazy {
        GetWordsRemoteDataSourceImpl(apiService)
    }

    private val apiService: ApiServiceImpl by lazy {
        ApiServiceImpl()
    }

}