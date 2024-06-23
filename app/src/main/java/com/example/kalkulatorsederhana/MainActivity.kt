package com.example.kalkulatorsederhana

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CalculatorApp()
        }
    }
}

@Composable
fun CalculatorApp() {
    var num1 by remember { mutableStateOf("") }
    var num2 by remember { mutableStateOf("") }
    var result by remember { mutableStateOf<String?>(null) }

    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth(),
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "Calculator", fontSize = 24.sp, modifier = Modifier.padding(bottom = 16.dp))

        TextField(
            value = num1,
            onValueChange = { num1 = it },
            label = { Text("Number 1") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        TextField(
            value = num2,
            onValueChange = { num2 = it },
            label = { Text("Number 2") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier.fillMaxWidth()
        ) {
            CalculatorButton("+", num1, num2) { n1, n2 -> n1 + n2 }
            CalculatorButton("-", num1, num2) { n1, n2 -> n1 - n2 }
            CalculatorButton("x", num1, num2) { n1, n2 -> n1 * n2 }
            CalculatorButton("/", num1, num2) { n1, n2 ->
                if (n2 != 0) n1 / n2 else {
                    null
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        result?.let {
            Text(text = "Result: $it", fontSize = 20.sp)
        }

        }
    }
}

@Composable
fun CalculatorButton(
    symbol: String,
    num1: String,
    num2: String,
    operation: (Int, Int) -> Int?
) {
    val context = LocalContext.current

    Button(onClick = {
        val n1 = num1.toIntOrNull()
        val n2 = num2.toIntOrNull()

        if (n1 != null && n2 != null) {
            val res = operation(n1, n2)
            if (res != null) {
                Toast.makeText(context, "Result is $res", Toast.LENGTH_SHORT).show()
            }
        } else {
            Toast.makeText(context, "Please enter valid numbers", Toast.LENGTH_SHORT).show()
        }
    }) {
        Text(text = symbol)
    }
}
