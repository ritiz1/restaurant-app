package com.example.restaurantapp

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DrawerState
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun drawerContent(
    navController: NavHostController?=null,
    drawerState: DrawerState?=null,
    scope: CoroutineScope?=null
) {
    ModalDrawerSheet {
        Text(
            text = "Profile",
            modifier = Modifier
                .padding(16.dp)
                .clickable {
                    navController?.navigate(Profile.route){
                        launchSingleTop= true
                        restoreState= true
                    }
                    scope?.launch { drawerState?.close() }
                }
        )
        // Add more items if needed
    }
    // Placeholder function to avoid errors
}