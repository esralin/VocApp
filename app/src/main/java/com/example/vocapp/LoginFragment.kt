package com.example.vocapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.example.vocapp.databinding.FragmentLoginBinding
import com.example.vocapp.databinding.FragmentSignUpBinding
import com.google.android.material.snackbar.Snackbar


class LoginFragment : Fragment() {

    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        val view = binding.root
        val application = requireNotNull(this.activity).application
        val dao = WordDatabase.getInstance(application)!!.wordDao
        val viewModelFactory = LoginViewModelFactory(dao)
        val viewModel = ViewModelProvider(
            this, viewModelFactory).get(LoginViewModel::class.java)
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner


       binding.loginButton.setOnClickListener{
           if(viewModel.loginCheck()){
               Snackbar.make(requireView(),"Login successful!", Snackbar.LENGTH_SHORT).show()
               val action = LoginFragmentDirections.actionLoginFragmentToSelectLevelFragment(viewModel.getEmail())
               view.findNavController().navigate(action)
           }
           else{
               Snackbar.make(requireView(),"Your email or password is wrong, try again.", Snackbar.LENGTH_SHORT).show()
           }
       }

        binding.signUpButton.setOnClickListener {
            view.findNavController()
                .navigate(R.id.action_loginFragment_to_signUpFragment)
        }



        return view

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}