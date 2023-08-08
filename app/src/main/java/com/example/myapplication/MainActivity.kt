package com.example.myapplication

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.example.myapplication.ui.theme.MyApplicationTheme
import com.example.myapplication.viewmodels.LoginViewModel
import com.example.myapplication.viewmodels.SignUpViewModel

class MainActivity : ComponentActivity() {
    private val signUpViewModel by viewModels<SignUpViewModel>()
    private val logInViewModel by viewModels<LoginViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
                val navController = rememberNavController()
                LaunchedEffect(key1 = true) {
                    logInViewModel.found.collect {
                        if (it)
                            navController.navigate("Data")
                        else
                            Toast.makeText(
                                this@MainActivity, "Name is not found", Toast.LENGTH_SHORT
                            ).show()
                    }
                }
                LaunchedEffect(key1 = true) {
                    signUpViewModel.successfulSignUp.collect {
                        if (it) {
                            Toast.makeText(
                                this@MainActivity,
                                "Registered Successfully",
                                Toast.LENGTH_SHORT
                            ).show()
                            navController.navigate("Login")
                        } else
                            Toast.makeText(
                                this@MainActivity,
                                "Fill all the fields",
                                Toast.LENGTH_SHORT
                            ).show()
                    }
                }
                LaunchedEffect(key1 = true) {
                    logInViewModel.goToSignUp.collect {
                        if (it) {
                            navController.navigate("SignUp")
                        }
                    }
                }
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background
                ) {
                    Host(
                        viewModelSignUp = signUpViewModel,
                        viewModelLogIn = logInViewModel,
                        navController = navController
                    )
                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun MainPagePreview() {
    MyApplicationTheme {
        // A surface container using the 'background' color from the theme
        Surface(
            modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background
        ) {


        }
    }

}
