package com.example.myapplication

import android.os.Bundle
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
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.ui.theme.MyApplicationTheme
import com.example.myapplication.ui.theme.primary

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
                    signUp(composeState)
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun signUp(composeState: ComposeState) {

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text = stringResource(id = R.string.sign_up),
            fontSize = 50.sp
            )
        NameTextField(
            name = composeState.email.value,
            onEmailChange ={
                composeState.onEmailTextChange(it)
            },
            label = stringResource(R.string.email),
            holder = stringResource(R.string.email_address)
        )
        NameTextField(
            name = composeState.name.value,
            onEmailChange ={
                composeState.onNameTextChange(it)
            },
            label = stringResource(R.string.name),
            holder= stringResource(R.string.fullname)
        )
        NameTextField(
            name = composeState.job.value,
            onEmailChange ={
                composeState.onJobTextChange(it)
            },
            label = stringResource(R.string.job),
            holder= stringResource(R.string.job)
        )
        PasswordTextField(
            password = composeState.password.value,
            visible = composeState.passwordVisibility.value,
            changeVisibility = { composeState.changePasswordVisibility(it) },
            onTextChange = { composeState.onPasswordChange(it) },
            label = stringResource(id =R.string.password),
            holder = stringResource(id =R.string.password)
        )

        PasswordTextField(
            password = composeState.rPassword.value,
            visible = composeState.rPasswordVisibility.value,
            changeVisibility = {
                composeState.changeRPasswordVisibility(it)
            },
            onTextChange = {
                composeState.onRPasswordChange(it)
            },
            label = stringResource(id= R.string.repeat_password),
            holder = stringResource(id =R.string.password)
        )
        Spacer(modifier = Modifier.height(15.dp))
        Button(
            onClick = {

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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NameTextField(
    name: String,
    onEmailChange: (String) -> Unit,
    label: String,
    holder: String
) {
    OutlinedTextField(
        value = name,
        onValueChange = { onEmailChange(it) },
        label = { Text(text = label) },
        placeholder = {
            Text(
                text = holder,
                fontSize = 25.sp
            )
        },
        singleLine = true,
        modifier = Modifier.fillMaxWidth(0.8f)
    )
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PasswordTextField(
    password: String,
    visible: Boolean,
    changeVisibility: (Boolean)->Unit,
    onTextChange: (String) -> Unit,
    label: String,
    holder: String
) {
    OutlinedTextField(
        value = password,
        onValueChange = { onTextChange(it) },
        trailingIcon = {
            IconButton(onClick = { changeVisibility(visible.not()) }) {
                Icon(
                    imageVector = ImageVector.vectorResource(id = R.drawable.password_eye),
                    tint = if (visible) primary else Color.Gray,
                    contentDescription = ""
                )
            }
        },
        label = {Text(text = label)},
        placeholder = {
            Text(
                text = holder,
                fontSize = 25.sp
            )
        },
        singleLine = true,
        modifier = Modifier.fillMaxWidth(0.8f),
        visualTransformation = if (visible) VisualTransformation.None else PasswordVisualTransformation()
    )
}

fun checkSignUp(
    email: String,
    name: String,
    job: String
):Boolean{
    return email.isNotEmpty()&&name.isNotEmpty()&&job.isNotEmpty()
}
fun checkPasswords(
    pass: String,
    pass2: String
):Boolean{
    return pass == pass2
}
@Preview(showBackground = true)
@Composable
fun signUpPreview() {
    MyApplicationTheme {

    }
}