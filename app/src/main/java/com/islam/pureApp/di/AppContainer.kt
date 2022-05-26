package com.islam.pureApp.di

import com.islam.pureApp.data.remote.api.ApiServiceImpl
import com.islam.pureApp.data.remote.datasource.GetWordsRemoteDataSourceImpl
import com.islam.pureApp.data.remote.repositories.GetWordsRepositoryImpl
import com.islam.pureApp.domain.usecases.MapWordsToWordListUseCase
import com.islam.pureApp.domain.usecases.WordsToWordListMapper
import com.islam.pureApp.presentation.viewmodel.MainViewModelFactory

class AppContainer {

    val viewModelFactory : MainViewModelFactory by lazy {
        MainViewModelFactory(useCase)
    }

    private val useCase: MapWordsToWordListUseCase by lazy {
        MapWordsToWordListUseCase(repository, wordsToWordListMapper)
    }

    private val repository: GetWordsRepositoryImpl by lazy {
        GetWordsRepositoryImpl(remoteDataSource)
    }

    private val wordsToWordListMapper: WordsToWordListMapper by lazy {
        WordsToWordListMapper()
    }

    private val remoteDataSource: GetWordsRemoteDataSourceImpl by lazy {
        GetWordsRemoteDataSourceImpl(apiService)
    }

    private val apiService: ApiServiceImpl by lazy {
        ApiServiceImpl()
    }

}