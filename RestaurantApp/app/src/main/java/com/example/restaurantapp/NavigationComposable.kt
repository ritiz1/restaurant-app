package com.example.restaurantapp

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json


@Composable
fun Navigation(startDestination:String,httpClient: HttpClient) {

    var navController = rememberNavController()
    NavHost(navController=navController,startDestination= startDestination){
        composable(Home.route){

            HomeScreen(
                navController, httpClient)
        }

        composable(Onboarding.route){
            onBoarding(navController)
        }

        composable(Profile.route){
            ProfileScreen(
                navController = navController
            )
        }

    }

}

