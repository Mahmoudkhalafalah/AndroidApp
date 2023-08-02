package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.myapplication.ui.theme.MyApplicationTheme

class LazyLists : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Print()
                }
            }
        }
    }
}

@Composable
fun Print() {
    LazyColumn {
        itemsIndexed(getDummyNames()) { index, item ->
            if (item.length <= 4) {
                Row(
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color.Gray)
                        .padding(20.dp)
                ) {
                    Text(text = item)
                }
            } else {
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color.DarkGray)
                        .padding(20.dp)
                ) {
                    Text(text = item)
                    Text(text = item)
                }
            }

            if (index.rem(4)==0&&index!=0) {
                LazyRow(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color.Cyan)
                        .padding(20.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceAround
                ) {
                    repeat(10) {
                        item {
                            Image(
                                painter = painterResource(id = R.drawable.user),
                                contentDescription = "", modifier = Modifier.size(60.dp)
                            )
                        }
                    }
                }
            }


        }
    }
}


private fun getDummyNames() = listOf(
    "John", "Emma", "Michael", "Olivia", "William",
    "Ava", "James", "Sophia", "Alexander", "Isabella",
    "Daniel", "Mia", "David", "Emily", "Joseph",
    "Charlotte", "Matthew", "Abigail", "Ethan", "Harper",
    "Christopher", "Amelia", "Andrew", "Evelyn", "Benjamin",
    "Elizabeth", "Joshua", "Sofia", "Jackson", "Avery",
    "Sebastian", "Ella", "Logan", "Grace", "Samuel",
    "Scarlett", "Ryan", "Chloe", "Henry", "Lily",
    "Nathan", "Eleanor", "Dylan", "Hannah", "Jacob",
    "Aria", "Lucas", "Layla", "Carter", "Victoria"
)

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MyApplicationTheme {
        Print()
    }
}