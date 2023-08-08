package com.example.myapplication

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navigation
import com.example.myapplication.screens.MainPage
import com.example.myapplication.screens.MessageList
import com.example.myapplication.screens.ProfileView
import com.example.myapplication.screens.SignUp
import com.example.myapplication.screens.getDummyProfiles
import com.example.myapplication.viewmodels.LoginViewModel
import com.example.myapplication.viewmodels.SignUpViewModel

@Composable
fun Host(
    viewModelSignUp: SignUpViewModel,
    viewModelLogIn: LoginViewModel,
    navController: NavHostController,
) {
    NavHost(
        navController = navController,
        startDestination = "Entry",
        modifier = Modifier.fillMaxSize()
    ) {
        navigation(
            startDestination = "Login",
            route = "Entry"
        ) {
            composable("Login") {
                MainPage(
                    email = viewModelLogIn.email.value,
                    changeEmail = { viewModelLogIn.onEmailTextChange(it) },
                    password = viewModelLogIn.password.value,
                    changePassword = { viewModelLogIn.onPasswordChange(it) },
                    passwordVisibility = viewModelLogIn.passwordVisibility.value,
                    changePasswordVisibility = { viewModelLogIn.changePasswordVisibility(it) },
                    checkedState = viewModelLogIn.checkedState.value,
                    changeCheckedState = { viewModelLogIn.changCheckedState(it) },
                    validEmail = viewModelLogIn.trueEmail.value,
                    validPass = viewModelLogIn.truePass.value,
                    buttonClicked = { viewModelLogIn.onButtonClick() },
                    buttonPressed = viewModelLogIn.buttonPressed.value,
                    changePressedState = { viewModelLogIn.changePressedState() },
                    signUp = { viewModelLogIn.toSignUpPage() }
                )
            }
            composable("SignUp") {
                SignUp(
                    email = viewModelSignUp.email.value,
                    changeEmail = { viewModelSignUp.onEmailTextChange(it) },
                    name = viewModelSignUp.name.value,
                    changeName = { viewModelSignUp.onNameTextChange(it) },
                    job = viewModelSignUp.job.value,
                    changeJob = { viewModelSignUp.onJobTextChange(it) },
                    passwordText = viewModelSignUp.password.value,
                    changePassword = { viewModelSignUp.onPasswordChange(it) },
                    passwordVisibility = viewModelSignUp.passwordVisibility.value,
                    changePasswordVisibility = { viewModelSignUp.changePasswordVisibility(it) },
                    rPasswordText = viewModelSignUp.rPassword.value,
                    changeRPassword = { viewModelSignUp.onRPasswordChange(it) },
                    rPasswordVisibility = viewModelSignUp.rPasswordVisibility.value,
                    changeRPasswordVisibility = { viewModelSignUp.changeRPasswordVisibility(it) },
                    buttonClick = { viewModelSignUp.signUpButtonClicked() },
                    validPass = viewModelSignUp.truePass.value,
                    matched = viewModelSignUp.passwordMatch.value
                )
            }
        }
        navigation(
            startDestination = "List",
            route = "Data"
        ) {
            composable("List") {
                MessageList(navController,profiles = getDummyProfiles())
            }
            composable(
                route = "Card/{name}&{job}",
                arguments =
                listOf(
                    navArgument("name") { type = NavType.StringType },
                    navArgument("job") { type = NavType.StringType }
                )
            ) {
                val name = it.arguments?.getString("name") ?: "name"
                val job = it.arguments?.getString("job") ?: "job"

                ProfileView(navController = navController, name = name, job = job)
            }
        }
    }

}
