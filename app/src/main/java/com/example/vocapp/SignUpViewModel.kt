package com.example.vocapp

import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import androidx.lifecycle.ViewModel


class SignUpViewModel(val dao: WordDao) : ViewModel() {

    var newName: String = ""
    var newEmail: String = ""
    var newPassword: String = ""

    var checkForNavigation: Boolean = false


    lateinit var returnedText: String
    fun addUser():String {

        if(newName.length <= 0 || newEmail.length<=0 || newPassword.length<=0){
            returnedText = "There is an information/s you did not fill in, please check again!"
            return returnedText
        }
        else if(dao.isTaken(newEmail)){
            returnedText = "This email is already registered, try a new email."
            return returnedText
        }
        else{
            val user = User()
            user.fullName = newName
            user.email = newEmail
            user.password = newPassword

            activeUser(user)
            checkForNavigation = true
            returnedText = "You have successfully registered. You can now login."
            return returnedText

        }
    }

    private fun activeUser(user: User){
        viewModelScope.launch {
            dao.insert(user)
        }
    }
}