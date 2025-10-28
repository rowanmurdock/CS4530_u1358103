package com.example.KTORFunFacts

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch


class FunFactViewModel(private val repository: Repository) : ViewModel() {

    val facts: StateFlow<List<FunFact>> =
        repository.allFacts.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )

    fun addNewFunFact() {
        viewModelScope.launch {
            repository.addNewFunFact()
        }
    }

}


object FunFactViewModelProvider {
    val Factory = viewModelFactory {
        initializer {
            val application = this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as FunFactApp
            FunFactViewModel(application.repository)
        }
    }
}