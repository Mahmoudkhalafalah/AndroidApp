package com.example.myapplication

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf

class ComposeState {

        private var     _checkedState =  mutableStateOf(false)
        val checkedState: State<Boolean> =_checkedState
        private val _passwordVisibility = mutableStateOf(false)
        fun changCheckedState(check: Boolean){
                _checkedState.value=check
        }

}