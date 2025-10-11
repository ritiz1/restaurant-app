package com.example.restaurantapp

import android.app.Application
import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.AndroidViewModel
import io.ktor.http.ContentType
import kotlinx.coroutines.launch
import kotlinx.serialization.json.Json

//This call will survive any configuration cahnges , holds the UI state , and connnect the UI to the repository.

/**
 * ViewModel for managing menu data and UI state.
 * This ViewModel interacts with the MenuRepository to fetch and store menu items.
 */
class MenuViewModel(application: Application): AndroidViewModel(application) {
    //Initialize the HTTP Client for the network operations
    private val httpClient = HttpClient{
        install (ContentNegotiation){
            json(json = Json {
                ignoreUnknownKeys = true
                isLenient = true
            },
                contentType = ContentType.Any
            )
        }
    }

    //Initialize the repository
    // Declare the repository property
    private val repository: MenuRepository // 2. This is the class property

    // Expose menu items to the UI as a StateFlow
    val menuItems: StateFlow<List<MenuItemEntity>>

    init {
        val menuDao = AppDatabase.getDatabase(application).menuDao()

        // 3. Initialize the CLASS property, don't create a new 'val'
        repository = MenuRepository(menuDao, httpClient)

        // Convert the Flow from the repository into a StateFlow
        menuItems = repository.menuItems.stateIn(
            scope = viewModelScope,
            started = kotlinx.coroutines.flow.SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )

        // Start fetching data
        refreshMenuData()
    }
    private fun refreshMenuData(){
        viewModelScope.launch{
            repository.refreshMenu()
        }


}
}