package com.example.restaurantapp

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.room.ColumnInfo
import androidx.room.Dao
import androidx.room.Database
import androidx.room.Entity
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.PrimaryKey
import androidx.room.Query
import androidx.room.Room
import androidx.room.RoomDatabase
import kotlinx.coroutines.flow.Flow

@Entity(tableName="restaurant_table")
data class MenuItemEntity(

    @PrimaryKey(autoGenerate = true)
    val id:Int = 0 ,

    @ColumnInfo(name = "price_usd")
    val price: Double,

    val name: String,
    val description: String,
    val imageUrl: String

)

@Dao
interface MenuDao {
    /**
     * Inserts a list of menu items. If an item already exists, it will be replaced.
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(menuItems: List<MenuItemEntity>)

    /**
     * Gets all menu items from the table and returns them as a Flow.
     * The UI can observe this Flow to get automatic updates when data changes.
     */
    @Query("SELECT * FROM restaurant_table")
    fun getAllMenuItems(): Flow<List<MenuItemEntity>>
}

// 3. THE DATABASE
// The main database class that ties the Entity and DAO together.
@Database(entities = [MenuItemEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun menuDao(): MenuDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "little_lemon_db"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}



//SO now we are mapping the data from the network to the database entity

