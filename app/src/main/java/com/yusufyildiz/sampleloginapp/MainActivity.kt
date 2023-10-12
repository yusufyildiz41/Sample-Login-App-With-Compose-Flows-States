package com.yusufyildiz.sampleloginapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.yusufyildiz.sampleloginapp.ui.components.LoginNavHost
import com.yusufyildiz.sampleloginapp.ui.login.LoginScreen
import com.yusufyildiz.sampleloginapp.ui.signup.SignUpScreen
import com.yusufyildiz.sampleloginapp.ui.theme.SampleLoginAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SampleLoginApp()
        }
    }
}

@Composable
fun SampleLoginApp(
    modifier: Modifier = Modifier
) {
    SampleLoginAppTheme {
        // A surface container using the 'background' color from the theme
        val navController = rememberNavController()
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            LoginNavHost(navController = navController)
            //LoginScreen()
        }
    }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    SampleLoginAppTheme {

    }
}