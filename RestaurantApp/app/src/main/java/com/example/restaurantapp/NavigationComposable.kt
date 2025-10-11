package com.example.restaurantapp

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import io.ktor.client.HttpClient


@Composable
fun Navigation(
    startDestination: String,
    viewModel: MenuViewModel,
    modifier: Modifier,
    navController: NavHostController
) {

    NavHost(navController=navController,startDestination= startDestination){
        composable(Home.route){

            HomeScreen(
                navController, viewModel)
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

