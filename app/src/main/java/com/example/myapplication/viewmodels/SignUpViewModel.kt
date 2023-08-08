package com.example.myapplication.viewmodels

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.screens.Profile
import com.example.myapplication.screens.profilesList
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

class SignUpViewModel : ViewModel() {

    private val _email = mutableStateOf("")
    val email: State<String> = _email

    private val _name = mutableStateOf("")
    val name: State<String> = _name

    private val _job = mutableStateOf("")
    val job: State<String> = _job

    private val _passwordVisibility = mutableStateOf(false)
    val passwordVisibility: State<Boolean> = _passwordVisibility

    private val _password = mutableStateOf("")
    val password: State<String> = _password

    private val _rPasswordVisibility = mutableStateOf(false)
    val rPasswordVisibility: State<Boolean> = _rPasswordVisibility

    private val _rPassword = mutableStateOf("")
    val rPassword: State<String> = _rPassword

    private val _truePass = mutableStateOf(false)
    val truePass: State<Boolean> = _truePass

    private val _passwordMatch = mutableStateOf(false)
    val passwordMatch: State<Boolean> = _passwordMatch

    private val _successfulSignUp = MutableSharedFlow<Boolean>()
    val successfulSignUp = _successfulSignUp.asSharedFlow()




    fun changePasswordVisibility(visible: Boolean) {
        _passwordVisibility.value = visible
    }


    fun changeRPasswordVisibility(visible: Boolean) {
        _rPasswordVisibility.value = visible
    }

    fun onEmailTextChange(text: String) {
        _email.value = text
    }

    fun onNameTextChange(text: String) {
        _name.value = text
    }

    fun onJobTextChange(text: String) {
        _job.value = text
    }


    fun onPasswordChange(text: String) {
        _password.value = text
        _truePass.value = isValidPassword(text)

    }

    fun onRPasswordChange(text: String) {
        _rPassword.value = text
        _passwordMatch.value = checkPasswords(text, password.value)
    }


    private fun isValidPassword(password: String): Boolean {
        return (password.length >= 8 && (password.contains('_') || password.contains('@') || password.contains(
            '#'
        ) || password.contains('$') || password.contains('&') || password.contains('%') || password.contains(
            '+'
        ) || password.contains('!')))
    }

    private fun isValidEmail(email: String): Boolean {
        return (email.isNotEmpty())
    }

    fun signUpButtonClicked() {
        var state = checkSignUp(
            email.value,
            name.value,
            job.value,
            password.value,
            rPassword.value
        ) && checkPasswords(password.value, rPassword.value)
        _passwordMatch.value = checkPasswords(password.value, rPassword.value)
        if(state)
            addName()
        viewModelScope.launch {
            _successfulSignUp.emit(state)
        }
    }

    private fun checkSignUp(
        email: String,
        name: String,
        job: String,
        password: String,
        rPassword: String
    ): Boolean {
        return email.isNotEmpty() && name.isNotEmpty() && job.isNotEmpty() && password.isNotEmpty() && rPassword.isNotEmpty()
    }

    private fun checkPasswords(
        pass: String,
        pass2: String
    ): Boolean {
        return pass == pass2
    }

    private fun addName() {
        profilesList.add(Profile(name.value, job.value))
    }

}