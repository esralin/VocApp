package com.example.vocapp

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController



class SelectLevelFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_select_level, container, false)

        val userEmail = SelectLevelFragmentArgs.fromBundle(requireArguments()).email
        
         val profileIcon = view.findViewById<ImageView>(R.id.profile_icon)

          profileIcon.setOnClickListener(){
              val action = SelectLevelFragmentDirections
                  .actionSelectLevelFragmentToProfileFragment(userEmail)
              view.findNavController().navigate(action)
          }


        val okButton = view.findViewById<Button>(R.id.levelSelectedButton)
        okButton.setOnClickListener(){
            view.findNavController()
                .navigate(R.id.action_selectLevelFragment_to_quizFragment)
        }



        return view


    }


}