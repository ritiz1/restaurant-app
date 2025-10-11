package com.example.restaurantapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.http.ContentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

class MainActivity : ComponentActivity() {

    // Your HttpClient is perfect here.
    private val httpClient by lazy {
        HttpClient(Android) {
            install(ContentNegotiation) {
                json(
                    json = Json {
                        ignoreUnknownKeys = true
                        isLenient = true
                    },
                    contentType = ContentType.Any
                )
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        // 1. --- THIS IS THE FIX ---
        // Perform the login check here, once, when the app starts.
        val sharedPreferences = getSharedPreferences("MyAppPrefs", MODE_PRIVATE)
        val isLoggedIn = sharedPreferences.getBoolean("IS_LOGGED_IN", false)
        val startDestination = if (isLoggedIn) Home.route else Onboarding.route

        setContent {
            // 2. We no longer need the AppRoot composable, making this simpler.
            val navController = rememberNavController()
            val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
            val scope = rememberCoroutineScope()
            val menuViewModel: MenuViewModel=viewModel()

            ModalNavigationDrawer(
                drawerState = drawerState,
                drawerContent = {
                    drawerContent(navController, drawerState, scope)
                }
            ) {
                Scaffold(
                    topBar = {
                        MyTopAppBar(
                            drawerState = drawerState,
                            scope = scope,
                            onCartClick = { /* Navigate to cart */ },
                            navController=navController
                        )
                    }
                ) { innerPadding ->
                    // 3. Pass the startDestination directly to your Navigation composable.
                    Navigation(
                        startDestination = startDestination,
                        viewModel = menuViewModel,


                        navController = navController,
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}
