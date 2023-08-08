package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.ui.theme.MyApplicationTheme

class MainActivity : ComponentActivity() {
    private val composeState = ComposeState()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {

                LaunchedEffect(key1 = true) {
                    composeState.found.collect {
                        if (it)
                            startActivity(Intent(this@MainActivity, MainActivity2::class.java))
                        else
                            Toast.makeText(
                                this@MainActivity, "Name is not found", Toast.LENGTH_SHORT
                            ).show()
                    }

                }
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background
                ) {
                    MainPage(
                        email = composeState.email.value,
                        changeEmail = { composeState.onEmailTextChange(it) },
                        password = composeState.password.value,
                        changePassword = { composeState.onPasswordChange(it) },
                        passwordVisibility = composeState.passwordVisibility.value,
                        changePasswordVisibility = { composeState.changePasswordVisibility(it) },
                        checkedState = composeState.checkedState.value,
                        changeCheckedState = { composeState.changCheckedState(it) },
                        validEmail = composeState.trueEmail.value,
                        validPass = composeState.truePass.value,
                        buttonClicked = { composeState.onButtonClick() },
                        buttonPressed = composeState.buttonPressed.value,
                        changePressedState = { composeState.changePressedState() }
                    )
                }


                //MessageList(profiles = getDummyProfiles())
            }
        }
    }
}


@Composable

fun MainPage(
    email: String,
    changeEmail: (String) -> Unit,
    password: String,
    changePassword: (String) -> Unit,
    passwordVisibility: Boolean,
    changePasswordVisibility: (Boolean) -> Unit,
    checkedState: Boolean,
    changeCheckedState: (Boolean) -> Unit,
    validEmail: Boolean,
    validPass: Boolean,
    buttonClicked: () -> Unit,
    buttonPressed: Boolean,
    changePressedState: () -> Unit
) {
    val mContext = LocalContext.current

    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier.align(Alignment.TopCenter),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(50.dp))
            Image(
                painter = painterResource(id = R.drawable.user), contentDescription = ""
            )
            Spacer(modifier = Modifier.height(50.dp))
            NameTextField(
                name = email,
                onEmailChange = {
                    changeEmail(it)
                },
                label = stringResource(id = R.string.email),
                holder = stringResource(id = R.string.email_address)
            )
            PasswordTextField(
                password = password,
                visible = passwordVisibility,
                changeVisibility = { changePasswordVisibility(it) },
                onTextChange = {
                    changePassword(it)

                },
                label = stringResource(id = R.string.password),
                holder = stringResource(id = R.string.password)
            )
            Spacer(modifier = Modifier.height(20.dp))
            Row(modifier = Modifier.fillMaxWidth()) {
                Checkbox(
                    checked = checkedState, onCheckedChange = {
                        changeCheckedState(it)
                    }, Modifier.padding(horizontal = 10.dp)
                )

                Text(
                    text = stringResource(R.string.remember_me), modifier = Modifier.padding(11.dp)
                )
            }
            Spacer(modifier = Modifier.height(60.dp))

            Text(
                text = if (!validPass && buttonPressed) stringResource(R.string.invalid_password) else "",
                color = Color.Red
            )
            Text(
                text = if (!validEmail && buttonPressed) stringResource(R.string.invalid_email) else "",
                color = Color.Red
            )

            Button(
                onClick = {
                    changePressedState()
                    buttonClicked()
                }, modifier = Modifier
                    .fillMaxWidth(0.4f)
                    .height(50.dp)
            ) {
                Text(
                    text = stringResource(R.string.sign_in), fontSize = 25.sp
                )
            }
            Spacer(modifier = Modifier.height(20.dp))
            Row {
                Text(
                    text = stringResource(R.string.don_t_have_account)
                )
                Spacer(modifier = Modifier.width(5.dp))
                Text(
                    text = stringResource(R.string.sign_up), modifier = Modifier.clickable {
                        mContext.startActivity(Intent(mContext, SignUp::class.java))
                    }, color = Color.Gray
                )


            }
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                modifier = Modifier.align(Alignment.CenterHorizontally),
                text = if (validEmail && validPass) {
                    "Welcome $email"
                } else "",
                color = Color.Green
            )
            Text(
                modifier = Modifier.align(Alignment.CenterHorizontally),
                text = if (validEmail && validPass && checkedState) {
                    stringResource(R.string.remembered)
                } else "",
                color = Color.Green
            )
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
