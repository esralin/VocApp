package com.example.vocapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.findNavController



class HomeFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)

        val startButton = view.findViewById<Button>(R.id.StartButton)


        startButton.setOnClickListener {
            view.findNavController()
                .navigate(R.id.action_homeFragment_to_loginFragment)
        }

        return view
    }


}