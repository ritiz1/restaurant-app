package com.example.restaurantapp

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

//Job of this class is to manage the menudata.
//It doesnt care about the UI , It just knows how to get the data from netwrok and
// how to store it in the room database.
class MenuRepository(
    private val menuDao: MenuDao,
    private val httpClient: HttpClient
)
{
    //This flow will automatically update the UI whenever there is change in database.
    val menuItems = menuDao.getAllMenuItems()

    //function the ViewModel will call to tell the repos
    //"hey, go get the latest menu from the internet. we are using suspend because parallel .
    suspend fun refreshMenu(){
        //Switch to the IO dispatcher for the network and db op to avoid blocking the main thread.
        withContext(Dispatchers.IO){
            try{
                //Fetch the raw data from the network
                val menuNetwork = httpClient
                    .get ("https://raw.githubusercontent.com/Meta-Mobile-Developer-PC/Working-With-Data-API/main/menu.json")
                    .body<MenuNetwork>()

                //Map the network data to my Room database entities now .
                val menuEntities = menuNetwork.menu.map { menuItems->
                    MenuItemEntity(
                        id = menuItems.id,
                        name = menuItems.title,
                        description = menuItems.description,
                        price = menuItems.price.toDoubleOrNull() ?: 0.0,
                        imageUrl = menuItems.image
                    )
                }

                //Now save this mapped data into the database.
                menuDao.insertAll(menuEntities)


            } catch (e: Exception){
                e.printStackTrace()
            }
        }
    }

}