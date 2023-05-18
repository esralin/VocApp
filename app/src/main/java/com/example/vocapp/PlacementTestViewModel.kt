package com.example.vocapp

import androidx.lifecycle.ViewModel


class PlacementTestViewModel(val dao: WordDao) : ViewModel() {

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
                a1Level -> totalScore += 0.01
                a2Level -> totalScore += 0.03
                b1Level -> totalScore += 0.05
                b2Level -> totalScore += 0.07
                c1Level -> totalScore += 0.09
            }
            return totalScore
        }
        else if(userAnswer == null) {
            return totalScore
        }
        else{
            when (wordlevel) {
                a1Level -> totalScore -= 0.09
                a2Level -> totalScore -= 0.07
                b1Level -> totalScore -= 0.05
                b2Level -> totalScore -= 0.03
                c1Level -> totalScore -= 0.01
            }
            if(totalScore < 0.0){
                totalScore = 0.0
            }
            return totalScore
        }

    }


}


