package com.example.myapplication

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class ComposeState: ViewModel() {

        private val _checkedState =  mutableStateOf(false)
        val checkedState: State<Boolean> =_checkedState

        private val _email = mutableStateOf("")
        val email: State<String> =_email

        private val _name = mutableStateOf("")
        val name: State<String> =_name

        private val _job = mutableStateOf("")
        val job: State<String> =_job

        private val _passwordVisibility = mutableStateOf(false)
        val passwordVisibility: State<Boolean> = _passwordVisibility

        private val _password = mutableStateOf("")
        val password: State<String> = _password

        private val _rPasswordVisibility = mutableStateOf(false)
        val rPasswordVisibility: State<Boolean> = _rPasswordVisibility

        private val _rPassword = mutableStateOf("")
        val rPassword: State<String> = _rPassword
        fun changCheckedState(check: Boolean){
                _checkedState.value=check
        }
        fun changePasswordVisibility(visible: Boolean){
                _passwordVisibility.value=visible
        }
        fun onPasswordChange(text: String){
                _password.value=text
        }
        fun changeRPasswordVisibility(visible: Boolean){
                _passwordVisibility.value=visible
        }
        fun onRPasswordChange(text: String){
                _password.value=text
        }
        fun onEmailTextChange(text: String){
                _email.value = text
        }
        fun onNameTextChange(text: String){
                _name.value = text
        }
        fun onJobTextChange(text: String){
                _job.value = text
        }

}