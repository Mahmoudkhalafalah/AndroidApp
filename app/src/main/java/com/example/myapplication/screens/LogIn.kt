package com.example.myapplication.screens

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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.NameTextField
import com.example.myapplication.PasswordTextField
import com.example.myapplication.R

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
    changePressedState: () -> Unit,
    signUp: () -> Unit,
) {

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
                        signUp()
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