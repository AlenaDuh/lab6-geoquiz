package com.example.geoquiz

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.geoquiz.ui.theme.GeoQuizTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            GeoQuizTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    GeoQuizScreen(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}
data class Question(
    val text: String,
    val answer: Boolean
)

val questions = listOf(
    Question("Canberra is the capital of Australia.", true),
    Question("The Pacific Ocean is larger than the Atlantic Ocean.", true),
    Question("The Suez Canal connects the Red Sea and the Indian Ocean.", false),
    Question("The source of the Nile River is in Egypt.", false),
    Question("The Amazon River is the longest river in the Americas.", true),
    Question("Lake Baikal is the world's oldest and deepest freshwater lake.", true)
)
@Composable
fun GeoQuizScreen(modifier: Modifier = Modifier) {
    // Индекс текущего вопроса
    var currentIndex by remember { mutableIntStateOf(0) }
    // Ответил ли пользователь на текущий вопрос
    var isAnswered by remember { mutableStateOf(false) }
    // Счётчик правильных ответов
    var score by remember { mutableIntStateOf(0) }
    // Показывать ли всплывающую панель с результатом
    var showResultDialog by remember { mutableStateOf(false) }

    val question = questions[currentIndex]

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Текст вопроса
        Text(
            text = question.text,
            fontSize = 20.sp,
            modifier = Modifier.padding(bottom = 24.dp)
        )

        // Кнопки True / False
        if (!isAnswered) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Button(onClick = { /* логика будет на следующем шаге */ }) {
                    Text("True")
                }
                Button(onClick = { /* логика будет на следующем шаге */ }) {
                    Text("False")
                }
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Кнопка Next
        Button(
            onClick = { /* логика будет на следующем шаге */ },
            enabled = isAnswered && currentIndex < questions.lastIndex
        ) {
            Text("Next")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GeoQuizScreenPreview() {
    GeoQuizTheme {
        GeoQuizScreen()
    }
}