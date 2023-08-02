package com.example.myapplication

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.sp
import com.example.myapplication.ui.theme.primary

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
                fontSize = 12.sp
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
    changeVisibility: (Boolean) -> Unit,
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
        label = { Text(text = label) },
        placeholder = {
            Text(
                text = holder,
                fontSize = 12.sp
            )
        },
        singleLine = true,
        modifier = Modifier.fillMaxWidth(0.8f),
        visualTransformation = if (visible) VisualTransformation.None else PasswordVisualTransformation()
    )
}