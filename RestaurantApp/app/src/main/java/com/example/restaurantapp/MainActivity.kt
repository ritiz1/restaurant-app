package com.example.restaurantapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.rememberNavController

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            //Getting SharedPreferences instance
            val sharedPreferences = getSharedPreferences("MyAppPrefs", MODE_PRIVATE)

            //Reading the login status. Default set to "False" if it is not found:
            val isLoggedIn = sharedPreferences.getBoolean("IS_LOGGED_IN", false)
            val startDestinations =if(isLoggedIn) Home.route else Onboarding.route
            Navigation(startDestinations)

        }
    }
}





