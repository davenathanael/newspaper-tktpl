package id.ac.ui.cs.mobileprogramming.davenathanael.newspaper.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import id.ac.ui.cs.mobileprogramming.davenathanael.newspaper.data.Tag

@Dao
interface TagDao {
    @Query("select * from tags")
    fun loadAll(): LiveData<List<Tag>>

    @Insert
    suspend fun insert(tag: Tag): Long
}