package com.example.vocapp

import androidx.lifecycle.ViewModel
import androidx.navigation.findNavController
import kotlinx.coroutines.flow.combine
import java.util.SplittableRandom


class QuizViewModel(val dao: WordDao) : ViewModel() {

    var totalScore: Double = 0.0
    val a1Level: String = "0"
    val a2Level: String = "1"
    val b1Level: String = "2"
    val b2Level: String = "3"
    val c1Level: String = "4"


  fun getNewQuestion(index: Int): String{

      var word = dao.getQuestion(index.toString())
      return word.definition
  }

    fun calculateScore(correctAnswer: String, userAnswer: String): Double{
        var wordlevel = dao.getWordByAnswer(correctAnswer).level


        if(userAnswer == correctAnswer){
            when (wordlevel) {
                a1Level -> totalScore += 0.15
                a2Level -> totalScore += 0.20
                b1Level -> totalScore += 0.25
                b2Level -> totalScore += 0.30
                c1Level -> totalScore += 0.35
            }
            return totalScore
        }
        else if(userAnswer == null) {
            return totalScore
        }
        else{
            when (wordlevel) {
                a1Level -> totalScore -= 0.35
                a2Level -> totalScore -= 0.30
                b1Level -> totalScore -= 0.25
                b2Level -> totalScore -= 0.20
                c1Level -> totalScore -= 0.15
            }
            if(totalScore < 0.0){
                totalScore = 0.0
            }
            return totalScore
        }

    }


}


