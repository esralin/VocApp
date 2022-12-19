package com.example.vocapp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class QuizViewModelFactory(private val selectedLevel: String,private val dao: WordDao)
    : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(QuizViewModel::class.java)) {
            return QuizViewModel(selectedLevel,dao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel")
    }
}