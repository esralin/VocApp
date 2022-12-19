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


        val levels = arrayOf("a1","a2","b1","b2","c1")
        val spinner = view.findViewById<Spinner>(R.id.spinner)

        spinner?.adapter = ArrayAdapter(activity?.applicationContext!!, androidx.transition.R.layout.support_simple_spinner_dropdown_item, levels) as SpinnerAdapter
        spinner?.onItemSelectedListener = object :AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {
                Toast.makeText(activity,"Please select a level!", Toast.LENGTH_LONG).show()
            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                 val type = parent?.getItemAtPosition(position).toString()
            }

        }


        val okButton = view.findViewById<Button>(R.id.levelSelectedButton)
        okButton.setOnClickListener(){
            val action = SelectLevelFragmentDirections
                .actionSelectLevelFragmentToQuizFragment(spinner.selectedItem as String)
            view.findNavController().navigate(action)
        }



        return view


    }


}