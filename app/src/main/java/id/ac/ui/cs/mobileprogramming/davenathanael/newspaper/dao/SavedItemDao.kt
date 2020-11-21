package id.ac.ui.cs.mobileprogramming.davenathanael.newspaper.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import id.ac.ui.cs.mobileprogramming.davenathanael.newspaper.data.SavedItem

@Dao
interface SavedItemDao {
    @Query("select * from saved_items")
    fun loadAll(): LiveData<List<SavedItem>>

    @Insert
    suspend fun insert(item: SavedItem): Long

    @Query("DELETE FROM saved_items")
    suspend fun deleteAll()
}