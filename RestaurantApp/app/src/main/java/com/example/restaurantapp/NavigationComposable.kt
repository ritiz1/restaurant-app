package com.example.restaurantapp

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController



@Composable
fun Navigation(startDestination:String) {

    var navController = rememberNavController()
    NavHost(navController=navController,startDestination= startDestination){
        composable(Home.route){
            HomeScreen(navController)
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

