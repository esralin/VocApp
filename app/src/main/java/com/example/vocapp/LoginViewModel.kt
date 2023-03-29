package com.example.vocapp
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import androidx.lifecycle.ViewModel
import androidx.navigation.findNavController


class LoginViewModel(val dao: WordDao) : ViewModel() {

    var userEmail: String = ""
    var userPassword: String = ""



    fun loginCheck(): Boolean{
        if(dao.login(userEmail,userPassword)){
            return true
        }
            return false
    }

    fun getEmail():String{
        return userEmail
    }
}