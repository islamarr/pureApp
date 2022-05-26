package com.islam.pureApp.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.islam.pureApp.domain.entites.Word
import com.islam.pureApp.domain.usecases.MapWordsToWordListUseCase
import java.util.concurrent.Executors

class MainViewModel(private val useCase: MapWordsToWordListUseCase) : ViewModel() {

    private val _wordsList = MutableLiveData<List<Word>>()
    val wordsList get() = _wordsList

    init {
        loadWordList()
    }

    fun loadWordList() {
      //  _wordsList.value = Response.loading(null)
        Executors.newSingleThreadScheduledExecutor().let {
            it.execute {
                val list = useCase.execute()
                _wordsList.postValue(list)

                it.shutdown()
            }
        }

    }
}