package com.islam.pureApp.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.islam.pureApp.domain.entites.Word
import com.islam.pureApp.domain.usecases.MapWordsToWordListUseCase
import java.util.concurrent.Executors

class MainViewModel(private val useCase: MapWordsToWordListUseCase) : ViewModel() {

    private val _wordsList = MutableLiveData<List<Word>>()
    val wordsList get() = _wordsList

    var currentSortType = SortType.DEFAULT
    var sortedList: List<Word> = listOf()

    init {
        loadWordList()
    }

    private fun loadWordList() {
        Executors.newSingleThreadScheduledExecutor().let {
            it.execute {
                val list = useCase.execute()
                _wordsList.postValue(list)
                it.shutdown()
            }
        }

    }

    fun sortList(sortType: SortType) {
        this.currentSortType = sortType
        _wordsList.value?.let { list ->
            sortedList = when (sortType) {
                SortType.DEFAULT -> {
                    list.sortedBy { it.id }
                }
                SortType.ASCEND -> {
                    list.sortedBy { it.count }
                }
                SortType.DESCEND -> {
                    list.sortedByDescending { it.count }
                }
            }
            _wordsList.postValue(sortedList)
        }

    }
}


enum class SortType {
    DEFAULT, ASCEND, DESCEND
}