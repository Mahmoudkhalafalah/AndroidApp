package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.ui.theme.MyApplicationTheme

class SignUp : ComponentActivity() {
    private val composeState by viewModels<ComposeState>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    signUp(
                        email = composeState.email.value,
                        changeEmail = { composeState.onEmailTextChange(it) },
                        name = composeState.name.value,
                        changeName = { composeState.onNameTextChange(it) },
                        job = composeState.job.value,
                        changeJob = { composeState.onJobTextChange(it) },
                        passwordText = composeState.password.value,
                        changePassword = { composeState.onSignPasswordChange(it) },
                        passwordVisibility = composeState.passwordVisibility.value,
                        changePasswordVisibility = { composeState.changePasswordVisibility(it) },
                        rPasswordText = composeState.rPassword.value,
                        changeRPassword = { composeState.onSignRPasswordChange(it) },
                        rPasswordVisibility = composeState.rPasswordVisibility.value,
                        changeRPasswordVisibility = { composeState.changeRPasswordVisibility(it) },
                        buttonClick = { composeState.signUpButtonClicked() },
                        success = composeState.successfulSignUp.value,
                        validPass = composeState.truePass.value,
                        matched = composeState.passwordMatch.value,
                        registerProfile = {composeState.addName()}
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun signUp(
    email: String,
    changeEmail: (String) -> Unit,
    name: String,
    changeName: (String) -> Unit,
    job: String,
    changeJob: (String) -> Unit,
    passwordText: String,
    changePassword: (String) -> Unit,
    passwordVisibility: Boolean,
    changePasswordVisibility: (Boolean) -> Unit,
    rPasswordText: String,
    changeRPassword: (String) -> Unit,
    rPasswordVisibility: Boolean,
    changeRPasswordVisibility: (Boolean) -> Unit,
    buttonClick: () -> Unit,
    success: Boolean,
    validPass: Boolean,
    matched: Boolean,
    registerProfile: ()-> Unit
) {
    val mContext = LocalContext.current

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(id = R.string.sign_up),
            fontSize = 50.sp
        )
        NameTextField(
            name = email,
            onEmailChange = {
                changeEmail(it)
            },
            label = stringResource(R.string.email),
            holder = stringResource(R.string.email_address)
        )
        NameTextField(
            name = name,
            onEmailChange = {
                changeName(it)
            },
            label = stringResource(R.string.name),
            holder = stringResource(R.string.fullname)
        )
        NameTextField(
            name = job,
            onEmailChange = {
                changeJob(it)
            },
            label = stringResource(R.string.job),
            holder = stringResource(R.string.job)
        )
        PasswordTextField(
            password = passwordText,
            visible = passwordVisibility,
            changeVisibility = { changePasswordVisibility(it) },
            onTextChange = { changePassword(it) },
            label = stringResource(id = R.string.password),
            holder = stringResource(id = R.string.password),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = if (!validPass) Color.Red else Color.Green,
                unfocusedBorderColor = if (!validPass) Color.Red else Color.Green
            )
        )

        PasswordTextField(
            password = rPasswordText,
            visible = rPasswordVisibility,
            changeVisibility = {
                changeRPasswordVisibility(it)
            },
            onTextChange = {
                changeRPassword(it)
            },
            label = stringResource(id = R.string.repeat_password),
            holder = stringResource(id = R.string.password),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = if (!matched) Color.Red else Color.Green,
                unfocusedBorderColor = if (!matched) Color.Red else Color.Green
            )
        )
        Spacer(modifier = Modifier.height(15.dp))
        Button(
            onClick = {
                buttonClick()
                Toast.makeText(
                    mContext,
                    if (!success) "Fill all the fields" else "Registered Successfully",
                    Toast.LENGTH_LONG
                ).show()
                if(success) {
                    registerProfile()
                    mContext.startActivity(Intent(mContext, MainActivity::class.java))
                }
            },
            modifier = Modifier
                .fillMaxWidth(0.4f)
                .height(50.dp)
        ) {
            Text(
                text = stringResource(R.string.sign_up),
                fontSize = 25.sp
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun signUpPreview() {
    MyApplicationTheme {

    }
}