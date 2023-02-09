package com.example.practicemvvm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class APIViewModelFactory(private val repository : SolvedAcAPIRepository) : ViewModelProvider.Factory {
    override fun <T: ViewModel> create(modelClass : Class<T>) : T {
        return modelClass.getConstructor(SolvedAcAPIRepository::class.java).newInstance(repository)
    }
}