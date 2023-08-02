package com.example.myapplication

import android.content.Intent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun app() {
    Card(
        elevation = CardDefaults.cardElevation(defaultElevation = 10.dp),
        shape = RoundedCornerShape(5.dp),
        colors = CardDefaults.cardColors(Color.White),
        modifier = Modifier
    ) {
        Column(
            modifier = Modifier,
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally

        ) {
            Image(painter = painterResource(id = R.drawable.user), contentDescription = "")

            Text(text = "John Doe", color = Color.Black)
            Text(text = "Software Engineer", color = Color.Black)
        }
    }
}

val profilesList: MutableList<Profile> = mutableListOf(
    Profile("John", "Software Engineer"),
    Profile("Alice", "Designer"),
    Profile("Bob", "Data Scientist"),
    Profile("Emily", "Product Manager"),
    Profile("Michael", "Marketing Specialist"),
    Profile("Olivia", "Teacher"),
    Profile("David", "Doctor"),
    Profile("Sophia", "Accountant"),
    Profile("Daniel", "Sales Manager"),
    Profile("Emma", "HR Manager"),
    Profile("William", "Graphic Designer"),
    Profile("Ava", "Lawyer"),
    Profile("James", "Chef"),
    Profile("Isabella", "Architect"),
    Profile("Alexander", "Financial Analyst"),
    Profile("Mia", "Journalist"),
    Profile("Ethan", "Mechanical Engineer"),
    Profile("Charlotte", "Nurse"),
    Profile("Oliver", "Photographer"),
    Profile("Amelia", "Researcher")
)

data class Profile(val name: String, val job: String)

fun getDummyProfiles(): MutableList<Profile> {
    return profilesList
}

@Composable
fun MessageList(profiles: List<Profile>) {
    val mContext = LocalContext.current
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(R.string.profiles),
            modifier = Modifier
                .height(50.dp)
                .background(Color.DarkGray)
                .fillMaxWidth(),
            textAlign = TextAlign.Center
                )

        LazyColumn(
            Modifier
                .weight(1F)
                .align(Alignment.Start)
                .fillMaxWidth()) {
            item {
                profiles.forEach {
                    Row(modifier = Modifier.padding(10.dp)) {
                        Column {
                            Image(
                                painter = painterResource(id = R.drawable.user),
                                contentDescription = "",
                                modifier = Modifier
                                    .size(50.dp)
                                    .clip(CircleShape)
                            )
                        }
                        Spacer(modifier = Modifier.width(5.dp))
                        Column {
                            Text(text = it.name)
                            Text(text = it.job)
                        }
                    }
                }
            }

        }
        Button(
            onClick = {
                mContext.startActivity(Intent(mContext, MainActivity::class.java))
            }, modifier = Modifier.height(50.dp)
        ) {
            Text(text = "Go to Sign In")
        }
    }
}