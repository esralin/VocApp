package com.example.vocapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import androidx.navigation.findNavController
import com.google.android.material.snackbar.Snackbar


class ProfileFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_profile, container, false)

        val userEmail = ProfileFragmentArgs.fromBundle(requireArguments()).email

        val application = requireNotNull(this.activity).application
        val dao = WordDatabase.getInstance(application)!!.wordDao

        val fullName =  view.findViewById<TextView>(R.id.userFullName)
        val email =  view.findViewById<TextView>(R.id.userEmail)
        val backButton =  view.findViewById<TextView>(R.id.backButton)
        val logoutButton =  view.findViewById<TextView>(R.id.logoutButton)
        val testScore = view.findViewById<TextView>(R.id.testScore)



        testScore.text = "0.0"
        if(dao.getScore() != null){
            testScore.text = dao.getScore().score.toString()
        }


        val user = dao.getUser(userEmail)

        fullName.text = user.fullName
        email.text = user.email

        backButton.setOnClickListener{
            val action = ProfileFragmentDirections
                .actionProfileFragmentToSelectLevelFragment(userEmail)
            view.findNavController().navigate(action)
        }

        logoutButton.setOnClickListener{
            view.findNavController()
                .navigate(R.id.action_profileFragment_to_loginFragment)
            Snackbar.make(requireView(),"You logged out.", Snackbar.LENGTH_SHORT).show()
        }



        return view
    }

}