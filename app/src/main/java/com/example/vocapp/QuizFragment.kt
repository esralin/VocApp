package com.example.vocapp

import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.example.vocapp.databinding.FragmentQuizBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder


class QuizFragment: Fragment() {

    private var _binding: FragmentQuizBinding? = null
    private val binding get() = _binding!!

    private var currentQuestionIndex : Int = 0
    private var groupIndex : Int = 0

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        _binding = FragmentQuizBinding.inflate(inflater, container, false)
        val view = binding.root


        val application = requireNotNull(this.activity).application
        val dao = WordDatabase.getInstance(application)!!.wordDao
        val viewModelFactory = QuizViewModelFactory(dao)
        val viewModel = ViewModelProvider(
            this, viewModelFactory,
        )[QuizViewModel::class.java]
        binding.viewModel = viewModel

        binding.wordDefinition.text = viewModel.getNewQuestion(4)


        binding.nextQuestionButton.setOnClickListener(){
            binding.wordDefinition.text = viewModel.getNewQuestion(currentQuestionIndex)
            binding.answer.text = ""
            binding.enteredWord.text.clear()

            currentQuestionIndex += 1
            groupIndex += 1

            if(currentQuestionIndex == 5){
                currentQuestionIndex = 0
            }

            var resultScore: String = ""
            resultScore = binding.totalScore.text.toString()

            val score = Score()
            score.score = resultScore.toDouble()
            dao.insertScore(score)

            if(groupIndex >= 5){
                val action = QuizFragmentDirections
                    .actionQuizFragmentToTestResultFragment(resultScore)
                view.findNavController().navigate(action)
            }

        }


        binding.checkAnswerButton.setOnClickListener(){
            var correctAnswer = dao.getWordByDefinition(binding.wordDefinition.text as String).word
            var userAnswer = binding.enteredWord.text.toString()

            var calculatedScore = viewModel.calculateScore(correctAnswer,userAnswer)
            val formattedScore = String.format("%.2f", calculatedScore)

            binding.answer.text = correctAnswer
            binding.totalScore.text = formattedScore

        }


        return view
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}