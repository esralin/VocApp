package com.example.vocapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.example.vocapp.databinding.FragmentQuizBinding


class QuizFragment: Fragment() {

    private var _binding: FragmentQuizBinding? = null
    private val binding get() = _binding!!

    private var currentScore: Double = 0.0
    private var currentLevel: Int = 0


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        _binding = FragmentQuizBinding.inflate(inflater, container, false)
        val view = binding.root

        val score = QuizFragmentArgs.fromBundle(requireArguments()).totalScore

        val application = requireNotNull(this.activity).application
        val dao = WordDatabase.getInstance(application)!!.wordDao
        val viewModelFactory = QuizViewModelFactory(dao)
        val viewModel = ViewModelProvider(
            this, viewModelFactory,
        )[QuizViewModel::class.java]
        binding.viewModel = viewModel


        currentScore  = score.toDouble()
        currentLevel  = viewModel.scoreToIndex(currentScore)
        viewModel.indexToLevel(currentLevel)
        viewModel.userScore = currentScore


        binding.wordDefinition2.text = viewModel.getNewQuestion(currentLevel)



        binding.nextQuestionButton2.setOnClickListener(){

            currentLevel = viewModel.determineLevel(currentScore)
            binding.wordDefinition2.text = viewModel.getNewQuestion(currentLevel)
            binding.answer2.text = ""
            binding.enteredWord2.text.clear()
        }


        var totalCorrectScore = 0
        var totalWrongScore = 0
        var count: Int = 0
        binding.checkAnswerButton2.setOnClickListener(){
            var correctAnswer = dao.getWordByDefinition(binding.wordDefinition2.text as String).word
            var userAnswer = binding.enteredWord2.text.toString()

            var wordlevel = dao.getWordByAnswer(correctAnswer).level

            var answerCorrectness = viewModel.controlCorrectness(correctAnswer,userAnswer)

            viewModel.calculateScore(wordlevel,answerCorrectness)

            count += 1


            if(count >= 20){
                currentScore += viewModel.updateScore()
                if(currentScore < 0.0){
                    currentScore = 0.0
                }
                viewModel.updateUserLevel(currentScore)
            }


            if(correctAnswer == userAnswer){
                totalCorrectScore += 1
            }
            else{
                totalWrongScore += 1
            }

            binding.answer2.text = correctAnswer
            binding.totalCorrectAnswer.text = "Correct Answer: "+"$totalCorrectScore"
            binding.totalWrongAnswer.text = "Wrong Answer: "+"$totalWrongScore"

        }


        return view
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}