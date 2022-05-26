package com.islam.pureApp.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.islam.pureApp.domain.usecases.MapWordsToWordListUseCase

class MainViewModelFactory(private val useCase: MapWordsToWordListUseCase) :
    ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            return MainViewModel(useCase) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}