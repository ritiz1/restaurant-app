package com.example.restaurantapp

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.example.restaurantapp.ui.theme.LittleLemonColor
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get

@Composable
fun HomeScreen(navController: NavHostController,viewModel:MenuViewModel) {
    val menuItems by viewModel.menuItems.collectAsState()
    var searchPhrase by remember { mutableStateOf("") }
    Column {
        UpperPanel()

        //The search button
        TextField(
            value = searchPhrase,
            onValueChange = { newValue -> searchPhrase = newValue },
            placeholder = {Text("Enter the Search Phrase")},
            leadingIcon = {
                Icon(imageVector = Icons.Default.Search, contentDescription ="Search Icon" )
            },

            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
        )

        //Now filtering the menu Items
        val filteredItems = if(searchPhrase.isNotBlank()){
            menuItems.filter{it.name.contains(searchPhrase, ignoreCase = true)}

        }
        else {
            menuItems
        }

        if (menuItems.isEmpty()) {
            // It's still good practice to show a loading indicator
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
        } else {
            // But once the data is loaded, show the real list
            LazyColumn(modifier = Modifier.fillMaxSize()) {
                items(filteredItems) { menuItem ->
                    MenuItemRow(menuItem = menuItem) // Your composable for a single item
                }
            }
        }



    }


}
// You create this function. It's the "template" for one row.
@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun MenuItemRow(menuItem: MenuItemEntity) {
    Card(onClick = {}) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(text = menuItem.name, style = MaterialTheme.typography.bodyLarge)
                Text(
                    text = "$${menuItem.price}", style = MaterialTheme.typography.bodySmall,
                    fontWeight = FontWeight.Bold
                )
            }
            GlideImage(
                model = menuItem.imageUrl,
                contentDescription = "${menuItem.name} image",
                modifier = Modifier.size(90.dp)
                    .clip(shape = RoundedCornerShape(4.dp))
            )
        }
    }
            Divider(
                modifier= Modifier.padding(start= 8.dp, end= 8.dp,top=4.dp, bottom=4.dp),
                thickness = 1.dp,color = LittleLemonColor.yellow)


}
