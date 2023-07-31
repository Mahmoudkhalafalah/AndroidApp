package com.example.myapplication
import android.content.Intent
import android.os.Bundle
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
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.ui.theme.MyApplicationTheme
import com.example.myapplication.ui.theme.primary

class MainActivity : ComponentActivity() {
    private val composeState = ComposeState()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MainPage(
                        checked = composeState.checkedState.value,
                        onCheckChanged = {
                            composeState.changCheckedState(it)
                        }

                    )
                    //MessageList(profiles = getDummyProfiles())
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable

fun MainPage(
    checked: Boolean,
    onCheckChanged:(Boolean)->Unit
) {
    val emailValue = remember {
        mutableStateOf("")
    }
    val passwordValue = remember {
        mutableStateOf("")
    }
    val passwordVisibility = remember { mutableStateOf(false) }
    val isTruePass = remember { mutableStateOf(true) }
    val isTrueEmail = remember { mutableStateOf(true) }
    val mContext = LocalContext.current

    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier.align(Alignment.TopCenter),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                modifier = Modifier.padding(50.dp),
                text = stringResource(R.string.internship),
                fontSize = 50.sp
            )
            Image(
                painter = painterResource(id = R.drawable.user),
                contentDescription = ""
            )



            Divider(modifier = Modifier.padding(50.dp))
            OutlinedTextField(
                value = emailValue.value,
                onValueChange = { emailValue.value = it },
                label = { Text(text = stringResource(R.string.email_address)) },
                placeholder = {
                    Text(
                        text = stringResource(R.string.email_address),
                        fontSize = 25.sp
                    )
                },
                singleLine = true,
                modifier = Modifier.fillMaxWidth(0.8f)

            )
            Spacer(modifier = Modifier.height(15.dp))

            OutlinedTextField(
                value = passwordValue.value,
                onValueChange = { passwordValue.value = it },
                trailingIcon = {
                    IconButton(onClick = { passwordVisibility.value = !passwordVisibility.value }) {
                        Icon(
                            imageVector = ImageVector.vectorResource(id = R.drawable.password_eye),
                            tint = if (passwordVisibility.value) primary else Color.Gray,
                            contentDescription = ""
                        )
                    }
                },
                label = {Text(text = stringResource(R.string.password))},
                placeholder = {
                    Text(
                        text = stringResource(R.string.password),
                        fontSize = 25.sp
                    )
                },
                singleLine = true,
                modifier = Modifier.fillMaxWidth(0.8f),
                visualTransformation = if (passwordVisibility.value) VisualTransformation.None else PasswordVisualTransformation()
            )
            Spacer(modifier = Modifier.height(20.dp))
            Row(modifier = Modifier.fillMaxWidth()) {
                Checkbox(
                    checked = checked,
                    onCheckedChange = {
                        onCheckChanged(it)
                    },
                    Modifier.padding(horizontal = 10.dp)
                )

                Text(text = stringResource(R.string.remember_me), modifier = Modifier.padding(11.dp))
            }
            Spacer(modifier = Modifier.height(60.dp))

            Button(
                onClick = {

                    isTruePass.value = isValidPassword(passwordValue.value)
                    isTrueEmail.value = isValidEmail(emailValue.value)
                    if(isTrueEmail.value&&isTruePass.value){
                        mContext.startActivity(Intent(mContext, MainActivity2::class.java))
                    }

                },
                modifier = Modifier
                    .fillMaxWidth(0.4f)
                    .height(50.dp)
            ) {
                Text(
                    text = stringResource(R.string.sign_in),
                    fontSize = 25.sp
                )
            }
            Spacer(modifier = Modifier.height(20.dp))
            Row {
                Text(
                    text = stringResource(R.string.doesn_t_have_account)
                )
                Spacer(modifier = Modifier.width(5.dp))
                Text(
                    text = stringResource(R.string.sign_up),
                    modifier = Modifier.clickable {
                        mContext.startActivity(Intent(mContext, SignUp::class.java))
                    },
                    color = Color.Gray
                )


            }
            Spacer(modifier = Modifier.height(10.dp))

            Text(text =if (isTruePass.value)"" else stringResource(R.string.invalid_password), color = Color.Red)
            Text(text =if (isTrueEmail.value)"" else stringResource(R.string.invalid_email), color = Color.Red)

            //Text(text =if (isTrueEmail.value&&isTruePass.value)"Signed In successfully" else "", color = Color.Green)
        }
    }

}

@Preview(showBackground = true)
@Composable
fun MainPagePreview() {

    val composeState = ComposeState()
    MyApplicationTheme {
        // A surface container using the 'background' color from the theme
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            MainPage(
                checked = composeState.checkedState.value,
                onCheckChanged = {
                    composeState.changCheckedState(it)


                }
            )
        }
    }

}
fun isValidPassword(password: String): Boolean {
    return (password.length >= 8 && (password.contains('_')||password.contains('@')||password.contains('#')||password.contains('$')||password.contains('&')||password.contains('%')||password.contains('+')||password.contains('!')))
}
fun isValidEmail(email: String): Boolean {
    return (email.isNotEmpty())
}