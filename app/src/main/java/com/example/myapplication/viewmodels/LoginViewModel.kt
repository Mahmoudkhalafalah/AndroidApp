package com.example.myapplication.viewmodels

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.screens.profilesList
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
class LoginViewModel : ViewModel(){
    private val _checkedState = mutableStateOf(false)
    val checkedState: State<Boolean> = _checkedState

    private val _email = mutableStateOf("")
    val email: State<String> = _email

    private val _passwordVisibility = mutableStateOf(false)
    val passwordVisibility: State<Boolean> = _passwordVisibility

    private val _password = mutableStateOf("")
    val password: State<String> = _password


    private val _truePass = mutableStateOf(false)
    val truePass: State<Boolean> = _truePass

    private val _trueEmail = mutableStateOf(false)
    val trueEmail: State<Boolean> = _trueEmail

    private val _buttonPressed = mutableStateOf(false)
    val buttonPressed: State<Boolean> = _buttonPressed

    private val _found = MutableSharedFlow<Boolean>()
    val found = _found.asSharedFlow()

    private val _goToSignUp = MutableSharedFlow<Boolean>()
    val goToSignUp = _goToSignUp.asSharedFlow()
    fun changCheckedState(check: Boolean) {
        _checkedState.value = check
    }

    fun changePasswordVisibility(visible: Boolean) {
        _passwordVisibility.value = visible
    }

    fun onPasswordChange(text: String) {
        _password.value = text
    }

    fun onEmailTextChange(text: String) {
        _email.value = text
    }

    fun onButtonClick() {
        _truePass.value = isValidPassword(password.value)
        _trueEmail.value = isValidEmail(email.value)
        viewModelScope.launch {
            _found.emit(search(_email.value))
        }
    }

    fun changePressedState() {
        _buttonPressed.value = true
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

    fun toSignUpPage(){
        viewModelScope.launch {
            _goToSignUp.emit(true)
        }
    }


    private fun search(text: String): Boolean {
        profilesList.forEach {
            if (it.name == text)
                return true
        }
        return false
    }
}