package id.ac.ui.cs.mobileprogramming.davenathanael.newspaper.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import id.ac.ui.cs.mobileprogramming.davenathanael.newspaper.data.SubscribedKeyword

@Dao
interface SubscribedKeywordDao {
    @Query("select * from subscribed_keywords")
    fun loadAll(): LiveData<List<SubscribedKeyword>>

    @Insert
    suspend fun insert(keyword: SubscribedKeyword): Long
}