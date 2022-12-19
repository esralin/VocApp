package com.example.vocapp

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.combine
import java.util.SplittableRandom


class QuizViewModel(val selectedLevel: String,val dao: WordDao) : ViewModel() {

  fun getNewQuestion(): String{
      var word = dao.getQuestion(selectedLevel)
      return word.definition
  }

    fun startWord(): String{
        var word = dao.getQuestion(selectedLevel)
        return word.definition
    }


}


