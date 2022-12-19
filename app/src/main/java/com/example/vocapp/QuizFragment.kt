package com.example.vocapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.vocapp.databinding.FragmentQuizBinding


class QuizFragment : Fragment() {

    private var _binding: FragmentQuizBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        _binding = FragmentQuizBinding.inflate(inflater, container, false)
        val view = binding.root
        val selectedLevel = QuizFragmentArgs.fromBundle(requireArguments()).selectedItem

        val application = requireNotNull(this.activity).application
        val dao = WordDatabase.getInstance(application)!!.wordDao
        val viewModelFactory = QuizViewModelFactory(selectedLevel,dao)
        val viewModel = ViewModelProvider(
            this, viewModelFactory,
        )[QuizViewModel::class.java]
        binding.viewModel = viewModel

        binding.wordDefinition.text = viewModel.startWord()



        binding.nextQuestionButton.setOnClickListener(){
            binding.wordDefinition.text = viewModel.getNewQuestion()
            binding.answer.text = ""
            binding.enteredWord.text.clear()
        }


        var totalCorrectScore = 0
        var totalWrongScore = 0
        binding.checkAnswerButton.setOnClickListener(){
            var answer = dao.getWordByDefinition(binding.wordDefinition.text as String).word
            if(answer == binding.enteredWord.text.toString()){
                totalCorrectScore += 1
            }
            else{
                totalWrongScore += 1
            }

            binding.answer.text = answer
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