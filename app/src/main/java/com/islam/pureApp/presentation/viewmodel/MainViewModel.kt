package com.islam.pureApp.presentation.viewmodel

import android.os.Handler
import android.os.Looper
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.islam.pureApp.data.remote.api.NetworkResponse
import com.islam.pureApp.data.remote.api.Response
import com.islam.pureApp.domain.entites.Word
import com.islam.pureApp.domain.usecases.GetWordListUseCase
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class MainViewModel(private val useCase: GetWordListUseCase) : ViewModel() {

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