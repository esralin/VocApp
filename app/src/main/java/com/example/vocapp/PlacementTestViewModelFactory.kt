package com.example.vocapp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class PlacementTestViewModelFactory(private val dao: WordDao)
    : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(PlacementTestViewModel::class.java)) {
            return PlacementTestViewModel(dao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel")
    }
}