package com.example.vocapp

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.navigation.findNavController
import kotlinx.coroutines.flow.combine
import java.lang.reflect.Array.get


class TestResultFragment : Fragment() {


    @SuppressLint("SuspiciousIndentation")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_test_result, container, false)

        val score = TestResultFragmentArgs.fromBundle(requireArguments()).totalScore


        val resultScore = view.findViewById<TextView>(R.id.resultScore)
        val englishLevel = view.findViewById<TextView>(R.id.englishLevel)

        resultScore.text = score


        var totalScore = score.toDouble()

            when (totalScore) {
                in 0.0..1.5 -> englishLevel.text = "A1"
                in 1.6..2.5 -> englishLevel.text = "A2"
                in 2.6..3.5 -> englishLevel.text = "B1"
                in 3.6..4.5 -> englishLevel.text = "B2"
                in 4.6..5.0 -> englishLevel.text = "C1"
            }










        return view
    }


}