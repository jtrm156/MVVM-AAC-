package com.example.practicemvvm.presentation.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.practicemvvm.data.SolvedAcAPIRepository

class APIViewModelFactory(private val repository : SolvedAcAPIRepository) : ViewModelProvider.Factory {
    override fun <T: ViewModel> create(modelClass : Class<T>) : T {
        return modelClass.getConstructor(SolvedAcAPIRepository::class.java).newInstance(repository)
    }
}