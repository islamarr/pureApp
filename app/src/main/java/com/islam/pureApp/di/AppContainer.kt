package com.islam.pureApp.di

import android.content.Context
import com.islam.pureApp.data.db.datasource.GetWordsLocalDataSource
import com.islam.pureApp.data.db.datasource.GetWordsLocalDataSourceImpl
import com.islam.pureApp.data.remote.api.ApiServiceImpl
import com.islam.pureApp.data.remote.datasource.GetWordsRemoteDataSourceImpl
import com.islam.pureApp.data.remote.repositories.GetWordsRepositoryImpl
import com.islam.pureApp.domain.usecases.MapWordsToWordListUseCase
import com.islam.pureApp.domain.usecases.WordsToWordListMapper
import com.islam.pureApp.presentation.viewmodel.MainViewModelFactory

class AppContainer(private val applicationContext: Context) {

    val viewModelFactory: MainViewModelFactory by lazy {
        MainViewModelFactory(useCase)
    }

    private val useCase: MapWordsToWordListUseCase by lazy {
        MapWordsToWordListUseCase(repository, wordsToWordListMapper)
    }

    private val wordsToWordListMapper: WordsToWordListMapper by lazy {
        WordsToWordListMapper()
    }

    private val repository: GetWordsRepositoryImpl by lazy {
        GetWordsRepositoryImpl(remoteDataSource, localDataSource)
    }

    private val localDataSource: GetWordsLocalDataSource by lazy {
        GetWordsLocalDataSourceImpl(applicationContext)
    }

    private val remoteDataSource: GetWordsRemoteDataSourceImpl by lazy {
        GetWordsRemoteDataSourceImpl(apiService)
    }

    private val apiService: ApiServiceImpl by lazy {
        ApiServiceImpl()
    }

}