package com.example.myapplication

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class ComposeState : ViewModel() {

    private val _checkedState = mutableStateOf(false)
    val checkedState: State<Boolean> = _checkedState

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

    private val _trueEmail = mutableStateOf(false)
    val trueEmail: State<Boolean> = _trueEmail

    private val _buttonPressed = mutableStateOf(false)
    val buttonPressed: State<Boolean> = _buttonPressed

    private val _passwordMatch = mutableStateOf(false)
    val passwordMatch: State<Boolean> = _passwordMatch

    private val _successfulSignUp = mutableStateOf(false)
    val successfulSignUp: State<Boolean> = _successfulSignUp

    private val _found = mutableStateOf(false)
    val found: State<Boolean> = _found
    fun changCheckedState(check: Boolean) {
        _checkedState.value = check
    }

    fun changePasswordVisibility(visible: Boolean) {
        _passwordVisibility.value = visible
    }

    fun onPasswordChange(text: String) {
        _password.value = text
    }

    fun changeRPasswordVisibility(visible: Boolean) {
        _rPasswordVisibility.value = visible
    }

    fun onRPasswordChange(text: String) {
        _rPassword.value = text
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

    fun onButtonClick() {
        _truePass.value = isValidPassword(password.value)
        _trueEmail.value = isValidEmail(email.value)
        _found.value = search(email.value)
    }

    fun changePressedState() {
        _buttonPressed.value = true
    }
    fun onSignPasswordChange(text: String) {
        _password.value = text
        _truePass.value = isValidPassword(text)

    }
    fun onSignRPasswordChange(text: String) {
        _rPassword.value = text
        _passwordMatch.value = checkPasswords(text,password.value)
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
        _passwordMatch.value = checkPasswords(password.value, rPassword.value)
        _successfulSignUp.value = checkSignUp(
            email.value,
            name.value,
            job.value,
            password.value,
            rPassword.value
        ) && checkPasswords(password.value, rPassword.value)
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

    fun addName(){
        profilesList.add(Profile(name.value,job.value))
    }

    private fun search (text: String): Boolean{
        profilesList.forEach{
            if(it.name==text)
                return true
        }
        return false
    }

}