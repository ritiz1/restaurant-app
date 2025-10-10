package com.example.restaurantapp

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.DrawerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyTopAppBar(
    drawerState: DrawerState?=null,
    scope: CoroutineScope?= null,
    onCartClick: () -> Unit,
    navController: NavHostController?=null
) {
    TopAppBar(
        title = {
            Row(
                modifier = Modifier.fillMaxWidth(), // <--- Takes up max space
                horizontalArrangement = Arrangement.Center // <--- Puts the content in the middle
            ) {
                Image(
                    painter= painterResource(id=R.drawable.littlelemonimgtxt),
                    contentDescription = "Logo",
                    // Keep your size modifier from before to stop it from being huge
                    modifier = Modifier.height(40.dp)
                        .clickable{
                            navController?.navigate(Home.route){
                                launchSingleTop= true
                                restoreState= true
                            }

                        }
                )
            }

        },
        navigationIcon = {
            IconButton(onClick = {
                scope?.launch { drawerState?.open() }
            }) {
                Icon(Icons.Default.Menu, contentDescription = "Menu")
            }
        },
        actions = {
            IconButton(onClick = onCartClick) {
                Icon(Icons.Default.ShoppingCart, contentDescription = "Cart")
            }
        }
    )
}
