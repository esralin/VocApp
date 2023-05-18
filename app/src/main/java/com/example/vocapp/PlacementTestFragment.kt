package com.example.vocapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.example.vocapp.databinding.FragmentPlacementTestBinding



class PlacementTestFragment: Fragment() {

    private var _binding: FragmentPlacementTestBinding? = null
    private val binding get() = _binding!!

    private var currentQuestionIndex : Int = 0
    private var groupIndex : Int = 0

    private var resultScore:String = "0.00"

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        _binding = FragmentPlacementTestBinding.inflate(inflater, container, false)
        val view = binding.root


        val application = requireNotNull(this.activity).application
        val dao = WordDatabase.getInstance(application)!!.wordDao
        val viewModelFactory = PlacementTestViewModelFactory(dao)
        val viewModel = ViewModelProvider(
            this, viewModelFactory,
        )[PlacementTestViewModel::class.java]
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

                val score = Score()
                score.score = resultScore.toDouble()
                dao.insertScore(score)



            if(groupIndex >= 20){
                val action = PlacementTestFragmentDirections
                    .actionQuizFragmentToTestResultFragment(resultScore)
                view.findNavController().navigate(action)
            }

        }


        binding.checkAnswerButton.setOnClickListener(){
            var correctAnswer = dao.getWordByDefinition(binding.wordDefinition.text as String).word
            var userAnswer = binding.enteredWord.text.toString()

            var calculatedScore = viewModel.calculateScore(correctAnswer,userAnswer)
            val formattedScore = String.format("%.2f", calculatedScore)
            resultScore = formattedScore

            binding.answer.text = correctAnswer

        }


        return view
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}