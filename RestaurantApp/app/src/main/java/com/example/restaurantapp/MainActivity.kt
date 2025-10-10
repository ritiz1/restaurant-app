package com.example.restaurantapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.rememberNavController
import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.http.ContentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json


class MainActivity : ComponentActivity() {

    private val httpClient by lazy {
        HttpClient(Android) {
            install(ContentNegotiation) {
                json(
                    // The Json configuration object you already have
                    json = Json {
                        ignoreUnknownKeys = true
                        isLenient = true
                    },
                    // THIS IS THE FIX: Tell Ktor to process any content type as JSON
                    contentType = ContentType.Any
                )
            }
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            //Getting SharedPreferences instance
            val sharedPreferences = getSharedPreferences("MyAppPrefs", MODE_PRIVATE)

            //Reading the login status. Default set to "False" if it is not found:
            val isLoggedIn = sharedPreferences.getBoolean("IS_LOGGED_IN", false)
            val startDestinations =if(isLoggedIn) Home.route else Onboarding.route
            Navigation(startDestinations,httpClient)

        }
    }
}





