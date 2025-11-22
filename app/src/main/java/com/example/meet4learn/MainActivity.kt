package com.example.meet4learn

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.example.meet4learn.ui.navigation.Meet4LearnNavHost
import com.example.meet4learn.ui.navigation.Screen
import com.example.meet4learn.ui.theme.Meet4LearnTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val appContainer = (application as Meet4LearnApplication).container

        setContent {
            Meet4LearnTheme {
                val navController = rememberNavController()

               Meet4LearnNavHost(
                    navHostController = navController,
                    appContainer = appContainer,
                    startDestination = Screen.Splash.route
               )
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Meet4LearnTheme {
        Greeting("Android")
    }
}