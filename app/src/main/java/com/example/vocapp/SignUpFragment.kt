package com.example.vocapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.example.vocapp.databinding.FragmentSignUpBinding
import com.google.android.material.snackbar.Snackbar

class SignUpFragment : Fragment() {

    private var _binding: FragmentSignUpBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSignUpBinding.inflate(inflater, container, false)
        val view = binding.root
        val application = requireNotNull(this.activity).application
        val dao = WordDatabase.getInstance(application)!!.wordDao
        val viewModelFactory = SignUpViewModelFactory(dao)
        val viewModel = ViewModelProvider(
            this, viewModelFactory).get(SignUpViewModel::class.java)
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        binding.signUpButton2.setOnClickListener(){
            Snackbar.make(view,viewModel.addUser(), Snackbar.LENGTH_SHORT).show()

            if(viewModel.checkForNavigation){
                view.findNavController()
                    .navigate(R.id.action_signUpFragment_to_loginFragment)
            }
        }



        return view
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}