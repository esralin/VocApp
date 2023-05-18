package com.example.vocapp
import androidx.lifecycle.ViewModel
import java.util.*
import kotlin.math.*


class QuizViewModel(val dao: WordDao) : ViewModel() {


    private val questionScoreA1 = 0.10
    private val questionScoreA2 = 0.35
    private val questionScoreB1 = 0.55
    private val questionScoreB2 = 0.75
    private val questionScoreC1 = 0.95


    var userLevel = ""
    var userScore = 0.0

    fun scoreToIndex(score: Double): Int{
        var assignedIndex: Int = 0
        when (score) {
            in 0.0..0.29 -> assignedIndex = 0
            in 0.3..0.49 -> assignedIndex = 1
            in 0.5..0.69 -> assignedIndex = 2
            in 0.7..0.89 -> assignedIndex = 3
            in 0.9..1.0 ->  assignedIndex = 4
        }
        return assignedIndex
    }

    fun indexToLevel(index: Int){
        when(index){
            0 -> userLevel = "0"
            1 -> userLevel = "1"
            2 -> userLevel = "2"
            3 -> userLevel = "3"
            4 -> userLevel = "4"
        }
    }

    fun getNewQuestion(index: Int): String{
        var word = dao.getQuestion(index.toString())
        return word.definition
    }

    fun determineLevel(score: Double): Int{
        var newScore = skewNormalDistribution(score)
        var newLevel = scoreToIndex(newScore)
        return newLevel
    }

    fun controlCorrectness(correctAnswer: String, userAnswer: String):Boolean{
        if(userAnswer == correctAnswer){
          return true
        }
        else{
            return false
        }
    }

    fun skewNormalDistribution(median: Double): Double {
        val alpha = -5.0
        val sigma = 1.0
        val t = median + alpha * sigma * sqrt(2 / PI)
        val x = Random().nextGaussian()
        val y = alpha * x + t
        val z = if (y < median) median - (median - y) * exp(-0.5 * (y - median) * (y - median) / (sigma * sigma)) else y
        return max(0.0, min(1.0, z))
    }



    val values = mutableListOf<Double>()

    fun calculateScore(questionLevel: String, answerCorrectness: Boolean){
        if(answerCorrectness == true){
            if (questionScore(questionLevel) > questionScore(userLevel)) {
            values.add(questionScore(questionLevel))

            if (values.size > 20) {
                values.removeAt(0)
            }
            }
        }
        else{
            if(questionScore(questionLevel) <= questionScore(userLevel)){
                values.add((questionScore(questionLevel)*-1))
                if (values.size > 20) {
                    values.removeAt(0)
                }
            }
        }

    }

    var sum = 0.0
    var totalScore = 0.0
    var resultScore = 0.0
    fun updateScore():Double{
        sum = values.sum()
        if(values.size > 0){
            totalScore = sum / (values.size.toDouble())
            if(totalScore < 0.0){
                return totalScore
            }
            resultScore = totalScore - userScore
            return resultScore
        }
        else{
            return 0.0
        }

    }


    fun updateUserLevel(score: Double) {
        userScore = score
        userLevel = when {
            score <= 0.29 -> "0"
            score <= 0.49 -> "1"
            score <= 0.69 -> "2"
            score <= 0.89 -> "3"
            else -> "4"
        }
    }

    fun questionScore(questionLevel: String): Double {
        return when (questionLevel) {
            "0" -> questionScoreA1
            "1" -> questionScoreA2
            "2" -> questionScoreB1
            "3" -> questionScoreB2
            else -> questionScoreC1
        }
    }
}